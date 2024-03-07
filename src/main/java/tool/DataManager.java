package tool;

import java.io.*;
import java.util.Scanner;

import exception.InputException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

public class DataManager {
    public static final String EVENT = "E";
    private static final String FILE_PATH = "data/zukeBot.txt";
    private static final String FOLDER_PATH = "data";
    public static final String TODO = "T";
    public static final String DEADLINE = "D";

    /**
     * Creates a folder to store the saved data if it does not exist.
     */
    public static void createFolder() {
        File parentDir = new File(FOLDER_PATH);
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Creates a file to store the saved data if it does not exist. However,
     * if the file already exists, it will not create a new one.
     */
    public static void createFile() {
        File textFile = new File(FILE_PATH);
        try {
            if (!textFile.createNewFile()) {
                ResponseManager.indentPrint(ResponseManager.RETURN_MESSAGE);
            } else {
                ResponseManager.indentPrint(ResponseManager.CREATE_FILE_MESSAGE);
            }
        } catch (IOException error) {
            ResponseManager.indentPrint(error.getMessage() + "\n");
        }
    }

    /**
     * Reads the saved data from the file to recover previous task list.
     * 
     * @return TaskList object containing the saved data.
     * @throws InputException if the file does not exist.
     */
    public static TaskList readSavedData() throws InputException {
        try (Scanner reader = new Scanner(new File(FILE_PATH))) {
            TaskList taskList = new TaskList();
            while (reader.hasNextLine()) {
                addToTaskList(taskList, reader.nextLine());
            }
            return taskList;
        } catch (IOException Error) {
            throw new InputException(ResponseManager.NO_FILE_ERROR);
        }
    }

    /**
     * Decodes the read data to create the respective task objects,
     * and adds them to the task list.
     * 
     * @param taskList TaskList object to store the saved data.
     * @param input String containing the saved data.
     * @throws InputException if the file is corrupted.
     */
    private static void addToTaskList(TaskList taskList, String input) throws InputException {
        int splitLimit = 3;
        String[] taskDetails = input.split(" / ", splitLimit);
        String[] information = MessageDecoder.decodeSavedData(taskDetails[2]);
        boolean isDone = taskDetails[1].equals("1");
        switch (taskDetails[0]) {
        case TODO:
            taskList.add(new Todo(isDone, information[0]));
            break;

        case DEADLINE:
            taskList.add(new Deadline(isDone, information));
            break;

        case EVENT:
            taskList.add(new Event(isDone, information));
            break;

        default:
            throw new InputException(ResponseManager.LOAD_FILE_ERROR);
        }
    }

    /**
     * Saves the current task list to the file in a simplified format.
     * 
     * @param data TaskList object containing the current task list.
     */
    public static void saveData(TaskList data) {
        try (FileWriter fw = new FileWriter(FILE_PATH, false)) {
            fw.write(data.listTasksForSave());
        } catch (IOException error) {
            ResponseManager.indentPrint(error.getMessage() + "\n" +
                    ResponseManager.SAVE_FILE_ERROR);
        }
    }

}
