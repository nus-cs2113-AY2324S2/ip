package alpaca.storage;

import alpaca.ui.Ui;
import alpaca.tasks.*;
import alpaca.exceptions.InvalidFileException;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

<<<<<<< HEAD
/**
 * Represents a storage to read and write tasks to a file
 */
=======
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

>>>>>>> master
public class Storage {
    private static final String FILE_PATH = "data/Alpaca.txt";
    private static final File file = new File(FILE_PATH);

    private static int isTaskDone; //keep track of task status in the file

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public static boolean isFileExist() {
        return file.exists();
    }

    /**
     * Read the contents of the file and add the tasks to the task list
     * @param tasks
     * @throws IOException
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
     * Convert the text from the file to a task
     * @param text
     * @return
     * @throws InvalidFileException
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
     * Restore the task list from the file
     * @return the task list restored from the file
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
     * @param taskList the task list to be saved
     */
    public static TaskList restoreTask() {
        TaskList tasks = new TaskList();
        startFileReader(tasks);
        return tasks;
    }

    /**
     * Write the task list to the file
     * @param filePath the file path
     * @param textToAdd the text to be added to the file
     * @throws IOException
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Start the file writer
     * @param taskList the task list to be written to the file
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
