package lotes.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

import lotes.task.*;
import lotes.ui.UserInterface;

import lotes.exception.IllegalValueException;

public class Storage {
    public static String directoryName = "./data/";
    public static String filename = "./data/storage.txt";

    public static final String DEFAULT_STORAGE_FILEPATH = "./data/storage.txt";
    public static final String DEFAULT_STORAGE_DIRECTORY = "./data/";

    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public Storage() throws InvalidStorageFilePathException {
        path = Paths.get(filename);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Loads the {@code AddressBook} data from this storage file, and then returns it.
     * Returns an empty {@code AddressBook} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public static void load() {
        try {
            readFile();

        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
            // other errors
        }
    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    /**
     * Creates the directory and file to store the tasks
     */
    public static void createFile() {
        try {
            Path dirPath = Paths.get(directoryName);
            if (!Files.exists(dirPath)){
                Files.createDirectory(dirPath);
                FileWriter fw = new FileWriter(filename);

                fw.close();
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Reads the file containing the tasks and storing them into the array
     * @throws FileNotFoundException
     */
    public static void readFile() throws FileNotFoundException {
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);

            while (scanner.hasNext()) {
                readFromStorage(scanner.nextLine());
            }

        } catch (FileNotFoundException | IndexOutOfBoundsException e) {
            createFile();
        }
    }

    /**
     * Update the current file with the updated task list
     */
    public static void updateFile(){
        try {
            FileWriter fw = new FileWriter(filename);
            StringBuilder sb = new StringBuilder();

            for (Task task : TaskList.taskList) {
                sb.append(task).append(UserInterface.separator);
            }

            fw.write(String.valueOf(sb));
            fw.close();
        } catch (IOException e) {
            createFile();
        }
    }

    public static void readFromStorage(String description) {
        String taskType = description.substring(1, 2);
        String isMarked = description.substring(4, 5);
        String des = description.substring(7);

        if (taskType.equals("T")) {
            if (isMarked.equals("X")) {
                Task newTask = new ToDo(des, true);
                TaskList.taskList.add(newTask);

            } else {
                Task newTask = new ToDo(des);
                TaskList.taskList.add(newTask);

            }

        } else if (taskType.equals("D")) {
            String[] by = des.split("by: ");
            String byDate = by[1].substring(0, by[1].length() - 1);

            if (isMarked.equals("X")) {
                Task newTask = new Deadline(des, byDate, true);
                TaskList.taskList.add(newTask);
            } else {
                Task newTask = new Deadline(des, byDate);
                TaskList.taskList.add(newTask);
            }

        } else if (taskType.equals("E")) {
            String[] from = des.split("from: ");
            String[] to = from[1].split(" to: ");
            String toTime = to[1].substring(0, to[1].length() - 1);

            if (isMarked.equals("X")) {
                Task newTask = new Event(des, to[0], toTime, true);
                TaskList.taskList.add(newTask);
            } else {
                Task newTask = new Event(des, to[0], toTime);
                TaskList.taskList.add(newTask);
            }
        }
    }

}
