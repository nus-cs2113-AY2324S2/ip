package daisy.storage;

import daisy.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The storage class handles storage related operations. It loads previous task data to the current task list at the start
 * of the program, and helps to store task data in the current task list back to the "Daisy.txt" file at the end of the program.
 */

public class Storage {

    private String storageLocation;
    private String defaultFileName = "\\Daisy.txt";
    private String filePath;

    /**
     * Constructs a Storage instance. It takes in the directory path of the "Daisy.txt" file for read and write operations.
     * @param storageLocation the directory path of the "Daisy.txt" storage file
     */
    public Storage(String storageLocation) {
        this.storageLocation = storageLocation;
        this.filePath = storageLocation + defaultFileName;
    }

    /**
     * Loads previous task data into the newly created task list. It first identifies the type of task stored by the first
     * comma separated entry, then breaks down the subsequent information such that a task of corresponding type is created.
     * If the directory path of the "Daisy.txt" file is not found, creates one.
     * @param tasks the task list that requires data to be loaded into
     */
    public void loadData(TaskList tasks) {
        File taskFile = new File(filePath);
        System.out.println("Please wait while Daisy loads your previous data!");
        try {
            Scanner entryReader = new Scanner(taskFile);
            while (entryReader.hasNextLine()) {
                String[] entryData = entryReader.nextLine().split(",");
                boolean setDone = "1".equals(entryData[1]);
                switch(entryData[0]) {
                    case "T":
                        tasks.createTodo(entryData[2], false, setDone);
                        break;
                    case "D":
                        tasks.createDeadline(entryData[2], entryData[3], false, setDone);
                        break;
                    case "E":
                        tasks.createEvent(entryData[2], entryData[3], entryData[4], false, setDone);
                        break;
                }
            }
            entryReader.close();
            System.out.println("Data is successfully loaded! Program will now begin.");
        } catch (FileNotFoundException e) {
            File taskFolder = new File(storageLocation);
            taskFolder.mkdirs();
            System.out.println("No previous data found! Daisy will create a new one for this entry!");
        }
    }

    /**
     * Saves existing task data from the current task list. If the "Daisy.txt" file do not exist, creates one. For every
     * task entry, it calls the corresponding save() method from the task classes to convert entries into comma separated
     * string values, then writes them into the "Daisy.txt" file. It will always overwrite all previous entries in the file.
     * <p>
     * Throws error message if the "Daisy.txt" file could not be created due to permission issues.
     * @param tasks the current task list that requires data to be saved
     * @see daisy.task.Task
     */
    public void saveData(TaskList tasks) {
        System.out.println("Daisy will begin saving the data for this entry!");
        try {
            FileWriter entryWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.getSize(); i++) {
                String entryInput = tasks.getTask(i).save() + "\n";
                entryWriter.write(entryInput);
            }
            entryWriter.close();
            System.out.println("Successfully saved file. Program will now exit.");
        } catch (IOException e) {
            System.out.println("Error has occurred when saving. This entry will not be saved!");
        }

    }
}
