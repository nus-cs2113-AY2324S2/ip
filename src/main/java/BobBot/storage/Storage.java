package BobBot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import BobBot.tasks.Task;
import BobBot.ui.Ui;
import taskList.TaskList;

/**
 * Implements a storage system that saves task details entered by the user.
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public class Storage {

    private static final String SAVE_DIR_PATH = "src/storage/";
    private static final String SAVE_FILE_PATH = "saveFile.txt";
    private static final String FULL_FILE_PATH = SAVE_DIR_PATH + SAVE_FILE_PATH;
    private static final File FILE = new File(FULL_FILE_PATH);
    private static File directory = new File(SAVE_DIR_PATH);;

    private static ArrayList<Task> allTasks = TaskList.getTaskList();

    public Storage() {
    
    }

    /**
     * Loads the saved file if it exists, else creates a new save file.
     * <p>It handles FileNotFoundException by creating a new save 
     * file if the saved file is not found.</p>
     */
    public static void loadFile() {
        try {
            System.out.println("\tLoading from saved file ...");
            Storage.loadFileContents(FILE);
        } catch (FileNotFoundException e) {
            System.out.println("\tSaved file not found! Creating new save file ...");
            Storage.createNewSaveFile(FULL_FILE_PATH);
        }
    }

    /**
     * Loads the contents of the saved file into the task list.
     * 
     * @param file the file to load the contents from
     * @throws FileNotFoundException if the file is not found
     */
    public static void loadFileContents(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            loadToList(fileScanner.nextLine());
        }

        Ui.displayList();
    }

    /**
     * Creates a new save file if it does not exist.
     * 
     * @param saveFilePath the relative path to the save file
     */
    public static void createNewSaveFile(String saveFilePath) {
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!FILE.exists()) {
                FILE.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An Error occurred: " + e);
        }

        System.out.println("\tReady! Type a command to begin.");
        Ui.drawLine(false);
    }

    /**
     * Saves the task list to the save file.
     * 
     * <p>It writes the task details to the save file in the format
     * <code>taskNumber|taskMarkedStatus|taskDescription</code></p>
     */
    public static void saveFile() {
        StringBuilder fileContents = new StringBuilder();
        String lineToAdd = new String();
        int numberOfTasks = TaskList.getNumberOfTasks();

        for (int taskIndex = 0; taskIndex < numberOfTasks; taskIndex += 1) {
            lineToAdd = createFileLine(taskIndex);
            fileContents.append(lineToAdd);
        }

        try {
            FileWriter fw = new FileWriter(FULL_FILE_PATH);
            fw.write(fileContents.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the file: " + e);
        }
    }

    /**
     * Creates a line to add to the save file.
     * 
     * @param taskIndex the index of the task to add to the save file
     * @return the line to add to the save file
     */
    private static String createFileLine(int taskIndex) {
        int taskNumberToDisplay = taskIndex;
        Task taskToSave = allTasks.get(taskIndex);
        int taskMarkedStatus = (taskToSave.getMarkedStatus()) ? 1 : 0;
        String taskDescription = taskToSave.getDescription();
        String textToAdd = String.format("%d|%d|%s\n", taskNumberToDisplay, taskMarkedStatus, taskDescription);

        return textToAdd;
    }

    /**
     * Loads the markings of the tasks when porting from the save file.
     * 
     * @param taskNum the task number to load the markings for
     * @param isMarked the marking status of the task
     */
    private static void loadMarkings(int taskNum, boolean isMarked) {
        if (isMarked) {
            allTasks.get(taskNum).markAsDone();
        }
    }

    /**
     * Loads the tasks from the save file into the task list.
     * 
     * @param nextLine the line to load the tasks from
     */
    private static void loadToList(String nextLine) {
        String[] taskItems = nextLine.split("\\|", -1);
        int taskNum = Integer.parseInt(taskItems[0]);
        boolean isMarked = (Integer.parseInt(taskItems[1]) == 1) ? true : false;
        String taskDetails = taskItems[2].toString();

        boolean isLoad = true;

        TaskList.addTask(taskDetails, isLoad);
        loadMarkings(taskNum, isMarked);

    }
}
