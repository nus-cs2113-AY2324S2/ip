package carrot.storage;

import carrot.task.Task;
import carrot.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Provides methods to handle loading and saving tasks to storage.
 */
public class Storage {

    private static String textFilePath = "data/listOfTasks.txt";
    private static String directoryPath = "data";

    /**
     * Loads the list of tasks from the text file storage.
     *
     * @return the list of tasks loaded from storage in ArrayList format
     */
    public static ArrayList<Task> loadListOfTasks() {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        File textFile = new File(textFilePath);
        File directory = new File(directoryPath);

        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!textFile.exists()) {
                textFile.createNewFile();
            }

            Scanner s = new Scanner(textFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = Parser.parseTaskFromTextFileLine(line);

                listOfTasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Unable to load from storage.Storage: " + e.getMessage());
        }

        return listOfTasks;
    }

    /**
     * Writes all tasks from ArrayList to text file storage.
     *
     * @param listOfTasks the list of tasks to be written to storage
     */
    public static void writeAllTasksToStorage(ArrayList<Task> listOfTasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(textFilePath));

            for (Task task : listOfTasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Unable to save task.Task Data: " + e.getMessage());
        }
    }
}
