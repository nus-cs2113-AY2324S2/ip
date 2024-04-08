package burger.Storage;

import burger.TaskList.Task;
import burger.TaskList.TaskList;
import burger.UI.BurgerException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static burger.UI.Utilities.printErrorMessage;

public class BurgerFileClass {
    static final int TDE_INDEX = 0;
    static final int MARK_INDEX = 1;

    static final int TASK_INDEX = 2;

    public static final String DEFAULT_PATHNAME = java.nio.file.Paths.get("data","burger.txt")
            .normalize().toString();
    private static String pathName;

    public BurgerFileClass() {
        pathName = DEFAULT_PATHNAME;
    }

    public BurgerFileClass(String filePath) {
        pathName = filePath;
    }

    /**
     * Retrieves the save file if there is any from the file path.
     * Prints out the list of tasks from the file.
     * If the file cannot be found from the file path, it throws an error.
     *
     * @param newList the list to store the tasks from the save file.
     */
    public static void getSaveFile(TaskList newList) {
        try {
            readFromFile(newList);
            System.out.println("File is found!");
            System.out.println("Current List:");
            newList.printTaskList();
        } catch (IOException e) {
            System.out.println("File is not found! But we will create one for you!");
        } catch (BurgerException e) {
            printErrorMessage(e);
        }
    }

    /**
     * Reads the file from the file path and stores it inside the list.
     *
     * @param list the list to store the tasks from the save file.
     * @throws IOException if the file cannot be found from the file path.
     */
    public static void readFromFile(TaskList list) throws IOException, BurgerException {
        Files.createDirectories(Paths.get("data"));
        File f = new File(pathName); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            try {
                String[] currLineArray = s.nextLine().split("\\|");
                char currTDE = currLineArray[TDE_INDEX].charAt(0);
                char currMark = currLineArray[MARK_INDEX].charAt(0);
                String currTask = currLineArray[TASK_INDEX];
                addFromSaveFile(currTDE, currMark, currTask, list);
                list.totalTasks++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BurgerException("Invalid Task Format!");
            }
        }
    }

    /**
     * Adds a task from the save file
     *
     * @param tde todo, deadline, event task type.
     * @param mark whether the task is marked.
     * @param task the name of the task.
     * @param list the list to store the tasks.
     */
    public static void addFromSaveFile(char tde, char mark, String task, TaskList list) throws BurgerException {
        if (tde != 'T' && tde != 'D' && tde != 'E') {
            throw new BurgerException("Invalid Task Format!");
        }
        Task currTask = new Task(task, tde);
        if (mark == 'X') {
            currTask.markDone();
        }
        list.add(currTask);
    }

    /**
     * Saves the current list to a file.
     * Throws an error if file path cannot be found.
     *
     * @param list the list of tasks to be read from.
     */
    public void setSaveFile(TaskList list) {
        System.out.print("Saving file");
        try {
            FileWriter fw = new FileWriter(pathName);
            int i = 0;
            String textToWrite;
            while (i < list.totalTasks) {
                textToWrite = list.getTask(i).getTDE() + "|" + list.getTask(i).getTick() + "|"
                        + list.getTask(i).getName();
                fw.write(textToWrite + System.lineSeparator());
                System.out.print(".");
                i++;
            }
            fw.close();
            System.out.println();
            System.out.println("File successfully saved!");
        } catch (IOException e) {
            System.out.println();
            System.out.println("OH NO! File not saved!");
        }
    }
}
