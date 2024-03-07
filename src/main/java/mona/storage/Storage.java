package mona.storage;

import java.io.Console;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import mona.exception.MonaException;
import mona.input.Parser;
import mona.output.ConsolePrint;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Task;
import mona.task.Todo;
import mona.util.Constants;

/**
 * Handles storage operations, such as saving and loading tasks to and from a .txt file.
 */
public class Storage {

    protected String filePath;
    protected boolean isCorrupted;

    /**
     * Constructor for Storage. Initializes the relative file path for storage.
     *
     * @param filePath The relative path to the .txt file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.isCorrupted = false; // set to false by default
    }

    public boolean isCorrupted() {
        return isCorrupted;
    }

    /**
     * Saves the list of tasks to the storage (.txt) file.
     *
     * @param tasks The list of tasks to be saved.
     */
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

    /**
     * Generates a string representation of the list of tasks for storage. It does so by
     * calling the 'generateSavedTaskFormat' method for each task in the list of tasks.
     *
     * @param tasks The list of tasks to be converted into a string.
     * @return The string representation of the tasks.
     */
    public static String generateTextToSave(ArrayList<Task> tasks) {
        String textToAdd = "";
        for (Task task: tasks) {
            if (task != null) {
                textToAdd += generateSavedTaskFormat(task) + System.lineSeparator();
            }
        }
        return textToAdd;
    }

    /**
     * Generates a string representation of a single task for storage.
     * For example, the task 'todo work' that is not yet done will be converted into: " T | 0 | hi "
     * T -> indicating a 'todo' task
     * 0 -> indicating that it is not done
     * hi -> the description of this 'todo' task
     *
     * @param task The task to be converted into a string.
     * @return The string representation of the task.
     */
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

    /**
     * Loads tasks from the storage file into a list.
     *
     * @return The list of tasks loaded from the file.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> taskList = null;
        try {
            ArrayList<String> dataItems = readFile();
            taskList = parse(dataItems);
        } catch (ArrayIndexOutOfBoundsException e) {
            isCorrupted = true;
        } catch (MonaException e) {
            ConsolePrint.printErrorMessage(e.getMessage());
            isCorrupted = true;
        } catch (IOException e) {
            isCorrupted = true;
        }

        if (taskList == null) {
            return new ArrayList<Task>();
        } else {
            return taskList;
        }
    }

    /**
     * Reads the storage file and returns its contents as a list of strings, where each line is
     * treated as a String object.
     *
     * @return The list of strings read from the file.
     * @throws MonaException If there is an issue creating the data directory or file.
     * @throws IOException If there is an issue reading the file.
     */
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

        ArrayList<String> dataItems = (ArrayList<String>)
                Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
        return dataItems;
    }

    /**
     * Parses a list of strings (obtained from the 'readFile' method) into a list of tasks.
     *
     * @param dataItems The list of strings to be parsed.
     * @return The list of tasks parsed from the strings.
     */
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
                System.out.println("Unknown task encountered.");
                isCorrupted = true;
                break;
            }
        }
        return tasks;
    }

    /**
     * Determines the completion status of a task from its string representation (the representation
     * in the .txt file)
     *
     * @param inputLine The string representation of the task.
     * @return True if the task is marked as done, false otherwise.
     */
    private static boolean getTaskDoneStatus(String inputLine) {
        String[] inputElements = inputLine.split("\\|");

        if (inputElements[1].trim().equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Extracts the command type and parameters from the string representation of a task (the representation
     * in the .txt file)
     *
     * @param inputLine The string representation of the task.
     * @return An array containing the command type and parameters.
     */
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

        Parser inputParser = new Parser(command);
        return inputParser.getCommandTypeAndParams();
    }
}
