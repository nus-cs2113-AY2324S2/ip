package hachi.storage;

import hachi.data.HachiException;
import hachi.data.TaskList;
import hachi.data.task.Deadline;
import hachi.data.task.Event;
import hachi.data.task.Task;
import hachi.data.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This file represents the Storage class for the chatbot Hachi.
 * Contains methods for handling storage of save files for Hachi.
 *
 * @author clarencepohh
 * @version 08/03/2024
 */

public class Storage {
    private final String filePath;
    private final TaskList tasksList;
    protected File folder = new File("hachidata");

    /**
     * The constructor for Storage.
     * Initializes the required String filePath and TaskList.
     *
     * @param filePath The String that contains the relative path to the save file for Hachi.
     */

    public Storage (String filePath) {
        this.filePath = filePath;
        this.tasksList = new TaskList();
    }

    /**
     * Checks if the folder for the save file for Hachi exists,
     * and if it does not exist, creates the folder required.
     *
     * @throws SecurityException If there is a Security Violation.
     */

    public void initializeData() throws SecurityException{
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Method that handles saving files for Hachi chatbot.
     * Writes the save file when there are changes made to the TaskList.
     *
     * @throws IOException If there are failed or interrupted I/O operations.
     */

    public void saveHandler() throws IOException {
        initializeData();

        try {
            // to clear text file
            FileWriter fw = new FileWriter(filePath, false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("\n\tCreating new task list save...");
        }

        FileWriter fw = new FileWriter(filePath);

        if (tasksList != null) {
            for (int i = 0; i < tasksList.getSize(); i++) {
                fw.write((tasksList.getSpecifiedTask(i).getSaveFormat()) + "\n");
            }
        }
        fw.close();
    }

    /**
     * Method that finds the save file for Hachi.
     * Attempts to find the save file, reads it and overwrites the TaskList with the
     * Tasks in the save file.
     *
     * @throws FileNotFoundException If the specified save file cannot be found.
     * @throws HachiException If the save file is corrupted.
     */

    public void readFile() throws FileNotFoundException, HachiException {
        File taskFile = new File(filePath);
        Scanner s = new Scanner(taskFile);

        while (s.hasNext()) {
            String[] line = s.nextLine().split(" \\| ");
            Task toAdd;

            switch (line[0]){
            case "T":
                toAdd = new Todo(line[2]);
                break;

            case "D":
                toAdd = new Deadline(line[2], line[3]);
                break;

            case "E":
                toAdd = new Event(line[2], line[3], line[4]);
                break;

            default:
                throw new HachiException(HachiException.CORRUPTED_SAVE_MESSAGE);
            }

            if (line[1].equals("X")) {
                toAdd.setCompleteness(true);
            }

            tasksList.add(toAdd);
        }
    }

    /**
     * Method that loads the save file and returns the TaskList after it is overwritten.
     *
     * @return The TaskList generated after reading the save file.
     * @throws HachiException If the save file is corrupted.
     * @throws FileNotFoundException If the specified save file cannot be found.
     */

    public ArrayList<Task> load() throws HachiException, FileNotFoundException {
        readFile();
        return tasksList.getTasksArrayList();
    }
}
