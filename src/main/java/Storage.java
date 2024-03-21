import Tasks.Deadline;
import Tasks.Event;
import Tasks.TasksList;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Storage helper class to load from and store into save file.
 */
public class Storage {
    private static final TasksList TASKS_LIST = CheeseBot.TASKS_LIST;

    /**
     * Takes in 2 files: an input file and a temporary output file. Stores data into temporary output file, then
     * replaces input file with temporary output file, and finally deleting the temporary output file.
     *
     * @param inFile Input file containing data to be loaded from when CheeseBot is loaded up again.
     * @param outFile Temporary output file to store final data
     */
    public void storeData(File inFile, File outFile) {
        String inFilePath = inFile.getPath();
        String outFilePath = outFile.getPath();

        storeDataIntoTempFile(outFilePath);
        copyData(inFilePath, outFilePath);
        deleteTempFile(outFilePath);
    }

    private static void storeDataIntoTempFile(String outFilePath) {
        try {
            TASKS_LIST.outputDataIntoFile(outFilePath);
        } catch (IOException e) {
            System.out.println("Cannot save to output file!");
            System.exit(1);
        }
    }

    private static void deleteTempFile(String outFilePath) {
        try {
            Files.delete(Paths.get(outFilePath));
        } catch (IOException e) {
            System.out.println("Temp file cannot be deleted");
            System.exit(1);
        }
    }

    private static void copyData(String inFilePath, String outFilePath) {
        try {
            Files.copy(Paths.get(outFilePath), Paths.get(inFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Data file cannot be replaced");
            System.exit(1);
        }
    }

    /**
     * Creates a file if it does not exist. Also creates the directories it needs if they do not exist.
     *
     * @param file The file that is being created.
     */
    public void createFile(File file) {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.exit(1);
        }
    }

    /**
     * Reads from the input file and stores all data into CheeseBot's tasksList.
     * It reads as single line at a time, deciphers the task type specified, creates the respective task type object,
     * and finally stores into CheeseBot's tasksList.
     *
     * @param inFile Input file containing data to be loaded from.
     * @throws FileNotFoundException Throws FileNotFoundException when input file cannot found at specific location.
     */
    public void readFromInputFile(File inFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(inFile);

        int tasksLoaded = 0;

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String[] arguments = nextLine.split(" \\| ");
            boolean isTaskDone = Boolean.parseBoolean(arguments[arguments.length - 1]);
            String taskName = arguments[1];

            switch (arguments[0]) {
            case "T":
                Todo newTodo = new Todo(taskName);
                newTodo.setTaskDone(isTaskDone);
                TASKS_LIST.addTask(newTodo);
                break;

            case "D":
                LocalDateTime by = LocalDateTime.parse(arguments[2]);
                Deadline newDeadline = new Deadline(taskName, by);
                newDeadline.setTaskDone(isTaskDone);
                TASKS_LIST.addTask(newDeadline);
                break;

            case "E":
                LocalDateTime from = LocalDateTime.parse(arguments[2]);
                LocalDateTime to = LocalDateTime.parse(arguments[3]);
                Event newEvent = new Event(taskName, from, to);
                newEvent.setTaskDone(isTaskDone);
                TASKS_LIST.addTask(newEvent);
                break;
            }
            tasksLoaded++;
        }

        if (tasksLoaded > 0) {
            System.out.println("\tSuccessfully loaded " + tasksLoaded + " tasks from save file.");
        }
        scanner.close();
    }
}