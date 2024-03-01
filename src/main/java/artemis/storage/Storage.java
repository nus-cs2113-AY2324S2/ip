package artemis.storage;

import artemis.errors.Errors;
import artemis.processing.Parser;
import artemis.tasks.Deadline;
import artemis.tasks.Event;
import artemis.tasks.Task;
import artemis.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    save format:
    first line: username
    following lines: task data
    taskData format: [T/D/E] | [M/U] | TaskName | Other Info (due, from/to)
 */

/**
 * Handles storage and retrieval of tasks and user data.
 */
public class Storage {
    private static final String DEFAULT_SAVE_FILEPATH = "./save.txt";

    private final String path;

    private String username;
    private ArrayList<Task> taskList;

    /**
     * Creates a Storage instance with the default save file path.
     *
     * @throws Errors.CorruptedSaveException If there is an error while processing the file.
     */
    public Storage() throws Errors.CorruptedSaveException {
        this(DEFAULT_SAVE_FILEPATH);
    }

    /**
     * Creates a Storage object with the given file path
     *
     * @param filePath String of file path to be set
     * @throws Errors.CorruptedSaveException If the save file was not parsed correctly
     */
    public Storage(String filePath) throws Errors.CorruptedSaveException {
        this.path = filePath;
    }

    /**
     * To retrieve the initialized username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * To retrieve the initialized task list
     *
     * @return taskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Attempts to load the save file given by path
     *
     * @throws Errors.CorruptedSaveException If there was a error while processing the file
     * @throws FileNotFoundException If the given file does not exist
     */
    public void loadSave() throws Errors.CorruptedSaveException, FileNotFoundException {
        this.taskList = new ArrayList<>();
        File saveFile = new File(this.path);

        if (!saveFile.exists() || !saveFile.isFile()) {
            throw new FileNotFoundException();
        }

        Scanner fileScan = new Scanner(saveFile);

        boolean usernameFound = false;
        while (fileScan.hasNext()) {
            if (!usernameFound) {
                this.username = fileScan.nextLine();
                usernameFound = true;
            }

            try {
                String[] currentTaskArray = fileScan.nextLine().split(" \\| ");
                String taskName = currentTaskArray[2];
                boolean isDone = currentTaskArray[1].equals("M");
                Task currentTask = Parser.parseSaveData(currentTaskArray, taskName, isDone);

                this.taskList.add(currentTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new Errors.CorruptedSaveException();
            }
        }

        System.out.println("[artemis]: saved data successfully retrieved!");
    }

    /**
     * Saves the given taskList into the output text file in the given path
     *
     * @param username Username to be saved
     * @param taskList ArrayList of Tasks to be saved
     */
    public void saveData(String username, ArrayList<Task> taskList) {
        try (FileWriter fw = new FileWriter(path, false)) {
            fw.write(username + System.lineSeparator());

            for (Task currentTask : taskList) {
                Class<? extends Task> currentClass = currentTask.getClass();
                String currentOutput = formatSaveData(currentTask, currentClass);

                fw.write(currentOutput);
            }

            System.out.println("[artemis]: successfully saved data!");

        } catch (IOException e) {
            System.out.println("IO ERROR" + e);
        }

    }

    /**
     * Helper method to format data to be exported into the save file
     *
     * @param currentTask Current Task that is being formatted
     * @param currentClass Current type of Task (To Do, Deadline, Event)
     * @return Output string to be written into the file
     */
    private String formatSaveData(Task currentTask, Class<? extends Task> currentClass) {
        String currentOutput = "";
        String currentTaskName = currentTask.getTaskName();
        String isDoneString = currentTask.getIsDone() ? "M" : "U";

        if (currentClass.equals(ToDo.class)) {
            currentOutput = String.format("T | %s | %s\n", isDoneString, currentTaskName);
        } else if (currentClass.equals(Deadline.class)) {
            currentOutput = String.format("D | %s | %s | %s\n", isDoneString, currentTaskName,
                    ((Deadline) currentTask).getDueDate());
        } else if (currentClass.equals(Event.class)) {
            currentOutput = String.format("E | %s | %s | %s | %s\n", isDoneString, currentTaskName,
                    ((Event) currentTask).getStartDateTime(), ((Event) currentTask).getEndDateTime());
        }
        return currentOutput;
    }
}
