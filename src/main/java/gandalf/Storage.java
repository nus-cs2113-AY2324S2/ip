package gandalf;

import action.Task;
import exception.FailedDirectoryCreationException;
import exception.FileEmptyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class deals with loading tasks from the save-file and saving tasks to the save-file.
 */
public class Storage {
    /** An ArrayList of Strings to store the previous tasks loaded from the save-file. */
    static ArrayList<String> previousTasks = new ArrayList<>();

    /**
     * Loads data from the save-file.txt and populates the listTasks ArrayList.
     *
     * @param listTasks The ArrayList where tasks will be populated.
     */
    public static void loadData(ArrayList<Task> listTasks) {
        try {
            Ui.printLoadingMessage();
            insertPreviousTasks("data/save-file.txt", listTasks);
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundMessage();
        } catch (FileEmptyException e) {
            Ui.printEmptyFileMessage();
        }
    }

    /**
     * Collates all previous tasks in the save-file and saves it into an Array List of commands in Strings.
     * The Array List will then be inserted to the current tasks list.
     *
     * @param filePath The file path where tasks are stored.
     * @param listTasks The ArrayList where tasks will be populated.
     * @throws FileEmptyException If the file is empty.
     * @throws FileNotFoundException If the file is not found.
     */
    private static void insertPreviousTasks (String filePath, ArrayList<Task> listTasks)
            throws FileEmptyException, FileNotFoundException {
        File saveFile = new File(filePath);
        Scanner load = new Scanner(saveFile);
        if (!load.hasNext()) {
            throw new FileEmptyException();
        }
        else {
            int taskIndex = 1;
            while (load.hasNext()) {
                String text = load.nextLine();
                String parsedPreviousTask = Parser.parsePreviousTask(text);
                previousTasks.add(parsedPreviousTask);
                if (text.contains("[X]")) {
                    previousTasks.add("mark " + taskIndex);
                }
                taskIndex += 1;
            }
        }
        for (String previousTask : previousTasks) {
            boolean hideInput = true;
            Ui.handleUserCommand(previousTask, listTasks, hideInput);
        }
        Ui.handleUserCommand("list", listTasks, false);
        load.close();
    }

    /**
     * Saves tasks in the save-file.txt.
     *
     * @param listTasks The ArrayList containing tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> listTasks) {
        try {
            createDirectory();
            String filePath = "./data/save-file.txt";
            FileWriter writer = new FileWriter(filePath);
            String concatenatedTasks = compileTasksAsString(listTasks);
            writer.write(concatenatedTasks);
            writer.close();

            Ui.printSuccessfulSaveMessage();

        } catch (FailedDirectoryCreationException e) {
            Ui.printFailedDirectoryCreationMessage();
        } catch(IOException e) {
            Ui.printCorruptedWriteMessage(e);
        }
    }

    /**
     * Creates the data directory if it does not exist.
     *
     * @throws FailedDirectoryCreationException If the directory creation fails.
     */
    private static void createDirectory() throws FailedDirectoryCreationException {
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            if (dataDirectory.mkdirs()) {
                Ui.printSuccessfulDirectoryCreationMessage();
            } else {
                throw new FailedDirectoryCreationException();
            }
        }
    }

    /**
     * Compiles tasks data into a single string to be saved in the file.
     *
     * @param listTasks The ArrayList containing tasks to be compiled.
     * @return The compiled data string.
     */
    private static String compileTasksAsString(ArrayList<Task> listTasks) {
        StringBuilder dataToSave = new StringBuilder();
        for (Task listTask : listTasks) {
            if (listTask != null) {
                dataToSave.append(listTask);
                dataToSave.append("\n");
            }
        }
        return dataToSave.toString();
    }
}
