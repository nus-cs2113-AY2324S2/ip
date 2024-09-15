package alpaca.storage;

import alpaca.ui.Ui;
import alpaca.tasks.*;
import alpaca.exceptions.InvalidFileException;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;


/**
 * Represents a storage to read and write tasks to a file
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles file operations for storing and retrieving the task list.
 */
public class Storage {
    private static final String FILE_PATH = "data/Alpaca.txt";
    private static final File file = new File(FILE_PATH);

    private static int isTaskDone; //keep track of task status in the file

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    /**
     * Checks if the storage file exists.
     *
     * @return {@code true} if the file exists, {@code false} otherwise.
     */
    public static boolean isFileExist() {
        return file.exists();
    }

    /**
     * Reads tasks from the file and adds them to the provided task list.
     *
     * @param tasks The task list to populate with tasks from the file.
     * @throws IOException If an error occurs during file reading.
     */
    private static void readFileContents(TaskList tasks) throws IOException {
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                tasks.addTask(textToTask(s.nextLine()));
                if (isTaskDone == 1) {
                    tasks.markLastTask();//mark the task added
                }
            }
        } catch (InvalidFileException e) {
            Ui.printErrorMessage(e.toString());
        }
    }

    /**
     * Converts a line of text from the file into a Task object.
     *
     * @param text The line of text representing a task.
     * @return The task represented by the text.
     * @throws InvalidFileException If the text format is invalid.
     */
    private static Task textToTask(String text) throws InvalidFileException {
        String[] split = text.split("\\|");
        isTaskDone = Integer.parseInt(split[1].strip());//keep track of task status in the file
        switch (split[0].strip()) {
        case "T":
            return new Todo(split[2].strip());

        case "D":
            LocalDateTime deadline = LocalDateTime.parse(split[3].strip(), formatter);
            return new Deadline(split[2].strip(), deadline);

        case "E":
            LocalDateTime from = LocalDateTime.parse(split[3].strip(), formatter);
            LocalDateTime to = LocalDateTime.parse(split[4].strip(), formatter);
            return new Event(split[2].strip(), from, to);

        default:
            throw new InvalidFileException();
        }
    }

    /**
     * Create an empty file if the file does not exist
     */
    public static void createEmptyFile() {
        try {
            if (file.createNewFile()) { // Use the shared File instance
                System.out.println("No existing task list, file created: " + file.getName());
                Ui.printLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            Ui.printLine();
        }
    }

    /**
     * Restores and returns the task list from the file.
     *
     * @return The task list populated with tasks from the file.
     */
    public static TaskList startFileReader(TaskList tasks) {
        try {
            readFileContents(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            Ui.printLine();
        }
        return tasks;
    }

    /**
     * Save the task list to the file
     */
    public static TaskList restoreTask() {
        TaskList tasks = new TaskList();
        startFileReader(tasks);
        return tasks;
    }

    /**
     * Writes the entire task list to the file, overwriting any existing content.
     *
     * @param filePath The path of the file where the task list will be saved.
     * @param textToAdd The string representation of the entire task list.
     * @throws IOException If an error occurs during file writing.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Initiates writing the current task list to the file.
     *
     * @param taskList The string representation of the task list to be written to the file.
     */
    public static void startFileWriter(String taskList) {
        String filePath = "data/Alpaca.txt";
        try {
            writeToFile(filePath, taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
