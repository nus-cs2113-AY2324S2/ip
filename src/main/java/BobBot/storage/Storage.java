package BobBot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import BobBot.tasks.Task;
import tasks.TaskList;

public class Storage {

    private static final String SAVE_DIR_PATH = "src/storage/";
    private static final String SAVE_FILE_PATH = "saveFile.txt";
    private static final String FULL_FILE_PATH = SAVE_DIR_PATH + SAVE_FILE_PATH;
    private static final File file = new File(FULL_FILE_PATH);
    private static File directory;

    private static ArrayList<Task> allTasks = TaskList.getTaskList();

    public Storage() {
        directory = new File(SAVE_DIR_PATH);

        try {
            System.out.println("\tLoading from saved file ...");
            loadFileContents(FULL_FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("\tSaved file not found! Creating new save file ...");
            createNewSaveFile(FULL_FILE_PATH);
        }
    }

    // adapted from
    // https://nus-cs2113-ay2324s2.github.io/website/schedule/week6/topics.html#w6-3-java-file-access
    private static void loadFileContents(String filePath) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            loadToList(fileScanner.nextLine());
        }

        TaskList.displayList();
    }

    private static void createNewSaveFile(String saveFilePath) {
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An Error occurred: " + e);
        }

        System.out.println("\tReady! Type a command to begin.");
    }

    // rewrite the whole file
    public void saveFile() {
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

    private static String createFileLine(int taskIndex) {
        int taskNumberToDisplay = taskIndex;
        Task taskToSave = allTasks.get(taskIndex);
        int taskMarkedStatus = (taskToSave.getMarkedStatus()) ? 1 : 0;
        String taskDescription = taskToSave.getDescription();
        String textToAdd = String.format("%d|%d|%s\n", taskNumberToDisplay, taskMarkedStatus, taskDescription);

        return textToAdd;
    }

    private static void loadMarkings(int taskNum, boolean isMarked) {
        if (isMarked) {
            allTasks.get(taskNum).markAsDone();
        }
    }

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
