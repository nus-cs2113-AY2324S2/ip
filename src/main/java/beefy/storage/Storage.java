package beefy.storage;

import java.io.File;
import beefy.BeefyException;
import beefy.task.*;
import beefy.ui.Ui;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the file storage of the Duke chatbot.
 * Responsible for loading/saving of the task data from/to the text file.
 */
public class Storage {
    private static final String FILE_PATH = "data/beefy.txt";

    /**
     * Loads the task list from the text file and returns the TaskList.
     *
     * @return The TaskList with the loaded tasks.
     * @throws BeefyException If there is corruption in text file.
     * @throws IOException If an I/O error occurs.
     */
    public static TaskList readDisk() throws BeefyException, IOException {
        TaskList userTasks = new TaskList();
        try {
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            int numOfTasks = 0;
            while (s.hasNext()) {
                String currLine = s.nextLine();
                String[] currParams = currLine.split(",");
                switch (currParams[0]) {
                case "T":
                    userTasks.addTask(currParams[2], true);
                    numOfTasks++;
                    if (currParams[1].equals("TRUE")) {
                        userTasks.markTask(numOfTasks, true);
                    }
                    break;
                case "D":
                    userTasks.addTask(currParams[2], currParams[3], true);
                    numOfTasks++;
                    if (currParams[1].equals("TRUE")) {
                        userTasks.markTask(numOfTasks, true);
                    }
                    break;
                case "E":
                    userTasks.addTask(currParams[2], currParams[3], currParams[4], true);
                    numOfTasks++;
                    if (currParams[1].equals("TRUE")) {
                        userTasks.markTask(numOfTasks, true);
                    }
                    break;
                default:
                    throw new BeefyException("OH NO! beefy.txt FILE IS CORRUPTED!");
                }
            }
        } catch (FileNotFoundException e) {
            createFolder();
        }
        return userTasks;
    }

    /**
     * Creates a directory to store beefy.txt for storage of tasks' data.
     *
     * @throws IOException If an I/O error occurs.
     */
    public static void createFolder() throws IOException {
            Ui.printMessage("Creating Storage file...");
            File f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
    }

    /**
     * Edits and saves the tasks' data to the text file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public static void updateDisk(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task currTask : taskList.getTasks()) {
            fw.write(currTask.toDiskFormat());
        }
        fw.close();
    }

    /**
     * Appends a task's data to the text file.
     *
     * @param taskLine The Disk formatted task's data to be appended to text file.
     * @throws IOException If an I/O error occurs.
     */
    public static void addToDisk(String taskLine) throws IOException {
        FileWriter fa = new FileWriter(FILE_PATH, true);
        fa.write(taskLine);
        fa.close();
    }
}
