package mona.storage;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import mona.exception.MonaException;
import mona.input.InputParser;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.Todo;
import mona.util.Constants;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveToStorage(ArrayList<Task> tasks) {
        String textToSave = generateTextToSave(tasks);

        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToSave);
            fw.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String generateTextToSave(ArrayList<Task> tasks) {
        String textToAdd = "";
        for (Task task: tasks) {
            if (task != null) {
                textToAdd += generateSavedTaskFormat(task) + System.lineSeparator();
            }
        }
        return textToAdd;
    }

    public static String generateSavedTaskFormat(Task task) {
        String output = "";
        String isDone = "0";

        if (task.isDone()) {
            isDone = "1";
        }

        if (task instanceof Deadline){
            output += "D | ";
        } else if (task instanceof Event) {
            output += "E | ";
        } else if (task instanceof Todo) {
            output += "T | ";
        }
        output += isDone + " | " + task.getDescription();

        return output;
    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = null;
        try {
            ArrayList<String> dataItems = readFile();
            taskList = parse(dataItems);
        } catch (IOException | MonaException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    private ArrayList<String> readFile() throws MonaException, IOException {
        File dataFolder = new File(Constants.DATA_FOLDER_PATH);
        File dataFile = new File(dataFolder, Constants.DATA_FILE_NAME);

        // Check if the 'data' directory exists, if not create it
        if (!dataFolder.exists()) {
            boolean wasDirectoryMade = dataFolder.mkdirs();
            if (!wasDirectoryMade) {
                throw new MonaException("Could not create data directory.");
            }
        }

        // Check if the 'tasks.txt' file exists, if not create it
        if (!dataFile.exists()) {
            try {
                boolean wasFileCreated = dataFile.createNewFile();
                if (!wasFileCreated) {
                    throw new MonaException("Could not create data file.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<String> dataItems = (ArrayList<String>) Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
        return dataItems;
    }

    private ArrayList<Task> parse(ArrayList<String> dataItems) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : dataItems) {
            if (line == null) {
                break;
            }
            String[] commandTypeAndParams = getCommandTypeAndParams(line);
            boolean isTaskDone = getTaskDoneStatus(line);
            String taskType = commandTypeAndParams[Constants.INDEX_COMMAND_TYPE];
            switch (taskType) {
            case("todo"):
                Task newTodo = new Todo(commandTypeAndParams[Constants.INDEX_DESCRIPTION]);
                if (isTaskDone) {
                    newTodo.markAsDone();
                }
                tasks.add(newTodo);
                break;
            case("deadline"):
                Task newDeadline = new Deadline(commandTypeAndParams[Constants.INDEX_DESCRIPTION],
                        commandTypeAndParams[Constants.INDEX_DEADLINE]);
                if (isTaskDone) {
                    newDeadline.markAsDone();
                }
                tasks.add(newDeadline);
                break;
            case("event"):
                Task newEvent = new Event(commandTypeAndParams[Constants.INDEX_DESCRIPTION],
                        commandTypeAndParams[Constants.INDEX_FROM_DATE],
                        commandTypeAndParams[Constants.INDEX_TO_DATE]);
                if (isTaskDone) {
                    newEvent.markAsDone();
                }
                tasks.add(newEvent);
                break;
            default:
                System.out.println("Unknown task encountered. Skipping");
                break;
            }
        }
        return tasks;
    }

    private static boolean getTaskDoneStatus(String inputLine) {
        String[] inputElements = inputLine.split("\\|");

        if (inputElements[1].trim().equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    private static String[] getCommandTypeAndParams(String inputLine) {
        String[] inputElements = inputLine.split("\\|");
        String command = "";
        String taskType = inputElements[0].trim();

        switch(taskType) {
        case("T"):
            command = "todo " + inputElements[2].trim();
            break;
        case("D"):
            command = "deadline " + inputElements[2].trim();
            break;
        case("E"):
            command = "event " + inputElements[2].trim();
            break;
        }

        InputParser inputParser = new InputParser(command);
        return inputParser.getCommandTypeAndParams();
    }
}
