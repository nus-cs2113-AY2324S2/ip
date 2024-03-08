package anonbot.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import anonbot.exception.ImportDataException;
import anonbot.misc.ImportParser;
import anonbot.task.Task;
import anonbot.task.TaskManager;

/**
 * A static class to help read and populate tasks from file.
 */
public class AnonBotFileReader extends AnonBotFile {
    /**
     * Gets the most recent task number from the save file.
     * The task number represents the number of tasks that has been created to date.
     *
     * @param fileReader Valid Scanner for the file that is to be read.
     * @return The most recent task number which also serves as a unique task ID.
     * @throws ImportDataException If the task number read is not numeric; or if the file is empty.
     */
    private static int getMostRecentTaskNumber(Scanner fileReader) throws ImportDataException {
        if (!fileReader.hasNextLine()) {
            throw new ImportDataException("File is empty");
        }
        try {
            String data = fileReader.nextLine();
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            throw new ImportDataException("First line Parameter is corrupted");
        }
    }

    /**
     * Populate tasks in the task list by reading from the save file.
     *
     * @param fileReader Valid Scanner for the file that is to be read.
     */
    private static void populateTasks(Scanner fileReader) {
        while (fileReader.hasNextLine()) {
            String rawData = fileReader.nextLine();
            try {
                String[] parsedData = ImportParser.convertToParsableTask(rawData);
                String taskType = parsedData[0];
                boolean isTaskDone = parsedData[1].equals("Y");
                int taskNumber = Integer.parseInt(parsedData[2]);
                String taskDescription = parsedData[3];

                switch (taskType) {
                case "todo":
                    TaskManager.createTask(taskDescription, Task.TaskType.TODO, taskNumber, isTaskDone);
                    break;
                case "deadline":
                    TaskManager.createTask(taskDescription, Task.TaskType.DEADLINE, taskNumber, isTaskDone);
                    break;
                case "event":
                    TaskManager.createTask(taskDescription, Task.TaskType.EVENT, taskNumber, isTaskDone);
                    break;
                default:
                    System.out.println("Import Error: Unknown Task Type");
                }
            } catch (ImportDataException e) {
                e.printErrorMessage();
            } catch (NumberFormatException e) {
                System.out.println("Error Processing Task Import data: "
                        + "Not a task number where a task number was expected");
            }
        }
    }

    /**
     * Reads the save file from the default location and populate tasks based on the file.
     */
    public static void loadAnonBotData() {
        File f = new File(getDefaultFileName());
        try {
            Scanner fileReader = new Scanner(f);
            int mostRecentTaskNumber = getMostRecentTaskNumber(fileReader);
            if (fileReader.hasNextLine()) {
                TaskManager.setTotalTasksCreated(mostRecentTaskNumber);
                populateTasks(fileReader);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Warning: Unable to find the task list to load.");
            return;
        } catch (ImportDataException e) {
            e.printErrorMessage();
            return;
        }
    }
}
