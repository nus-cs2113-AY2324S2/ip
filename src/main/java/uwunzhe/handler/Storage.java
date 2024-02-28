package uwunzhe.handler;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import uwunzhe.util.TaskList;
import uwunzhe.tasks.Task;
import uwunzhe.tasks.TaskType;
import uwunzhe.tasks.Todo;
import uwunzhe.tasks.Deadline;
import uwunzhe.tasks.Event;
import uwunzhe.exceptions.UwunzheException;
import uwunzhe.exceptions.ExceptionMessages;

public class Storage {
    // Storage directory constants
    private static File storage;
    private final static String STORAGE_FOLDER_PATH = "./data";
    private final static String STORAGE_PATH = STORAGE_FOLDER_PATH + "/uwunzhe.txt";
    private final static String SEPARATOR = String.valueOf(Character.toChars(31));

    /**
     * Constructor for StorageHandler.
     * 
     * @param taskList The list of tasks to be updated, of type {@link TaskList}.
     * @throws UwunzheException If the storage file cannot be created.
     */
    public Storage(TaskList taskList) throws UwunzheException {
        // Create data folder if it does not exist
        if (!new File(STORAGE_FOLDER_PATH).exists()) {
            new File(STORAGE_FOLDER_PATH).mkdir();
        }

        // Create data file if it does not exist
        storage = new File(STORAGE_PATH);
        try {
            storage.createNewFile();
        } catch (IOException | SecurityException e) {
            throw new UwunzheException(ExceptionMessages.STORAGE_FILE_NOT_CREATED);
        }

        // Load data from file
        loadData(taskList);
    }

    /**
     * Initializes the scanner for the storage file.
     * 
     * @return The scanner for the storage file.
     * @throws UwunzheException If the storage file cannot be found.
     */
    public Scanner initStorageScanner() throws UwunzheException {
        try {
            return new Scanner(storage);
        } catch (FileNotFoundException e) {
            throw new UwunzheException(ExceptionMessages.STORAGE_FILE_NOT_READ);
        }
    }

    /**
     * Loads data from the storage file.
     * 
     * @param taskList The list of tasks to load to.
     * @throws UwunzheException If the storage file cannot be found.
     */
    public void loadData(TaskList taskList) throws UwunzheException {
        Scanner sc = initStorageScanner();
        while (sc.hasNextLine()) {
            try {
                String data = sc.nextLine();
                String[] dataStrings = data.split(SEPARATOR);
                createTask(taskList, dataStrings);
            } catch (UwunzheException e) {} // Ignore invalid data
        }
        sc.close();
    }

    /**
     * Creates a task from a line in the saved data and adds it to the list.
     * 
     * @param taskList The list of tasks to add to, of type {@link TaskList}.
     * @param data Array of strings representing the data to create the task from.
     * @throws UwunzheException If the storage data is invalid.
     */
    public void createTask(TaskList taskList, String[] data) throws UwunzheException {
        boolean isDone = data[1].equals("1");
        boolean isNotDone = data[1].equals("0");
        Task task;

        // Check if data is valid
        if (!isDone && !isNotDone) {
            throw new UwunzheException(ExceptionMessages.INVALID_STORAGE_CONTENT);
        }

        if (data[0].equals(TaskType.TODO.getType())) {
            task = new Todo(data[2], isDone);

        } else if (data[0].equals(TaskType.DEADLINE.getType())) {
            LocalDate endString = DateHandler.parseDate(data[3]);
            task = new Deadline(data[2], endString, isDone);

        } else if (data[0].equals(TaskType.EVENT.getType())) {
            LocalDate startString = DateHandler.parseDate(data[3]);
            LocalDate endString = DateHandler.parseDate(data[4]);
            task = new Event(data[2], startString, endString, isDone);

        } else {
            // Invalid data
            throw new UwunzheException(ExceptionMessages.INVALID_STORAGE_CONTENT);
        }

        // Add task to list
        taskList.addItem(task);
    }

    /**
     * Saves the data to the storage file.
     * 
     * @param taskList The list of tasks to save.
     * @throws UwunzheException If the storage file cannot be written to.
     */
    public void saveData(TaskList taskList) throws UwunzheException {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH);
            
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                String data = task.toStorageString(SEPARATOR);
                fw.write(data + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new UwunzheException(ExceptionMessages.STORAGE_FILE_NOT_WRITTEN);
        }
    }
}
