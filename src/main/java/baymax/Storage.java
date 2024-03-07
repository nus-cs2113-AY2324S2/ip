package baymax;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads and saves the tasks in the tasks.
 */

public class Storage {
    public static final String FILE_PATH = "data/baymax.txt";

    /**
     * Loads the tasks from the baymax.txt file into the tasks.
     *
     * @param tasks the ArrayList containing the Task objects.
     * @throws IOException If an I/O error occurred.
     */
    public static void loadTasks(ArrayList<Task> tasks) throws IOException {

        File folder = new File("data");
        // Folder "data" does not exist
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File(FILE_PATH);
        // File does not exist
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner s = new Scanner(file);

        while (s.hasNextLine()) {
            String line = s.nextLine();
            Task task = getTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    /**
     * Saves the tasks from the tasks into the baymax.txt file.
     *
     * @param tasks the ArrayList containing the Task objects.
     * @throws IOException If an I/O error occurred.
     */
    public static void saveTasks(ArrayList<Task> tasks) throws IOException {

        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            fileWriter.write(task.toFileString() + System.lineSeparator());

        }
        fileWriter.close();
    }

    /**
     * Returns a Task object that is extracted from the baymax.txt file.
     *
     * @param line the String of the data in the baymax.txt file.
     * @return a Task object.
     */
    public static Task getTask(String line) {
        Task task = null;
        String[] details = line.split(" \\| ");
        String taskType = details[0];
        String taskDescription = details[2];

        switch (taskType) {

            case "T":
                task = new ToDo(taskDescription);
                break;
            case "D":
                task = new Deadline(taskDescription);
                break;
            case "E":
                task = new Event(taskDescription);
                break;
            // Not a Task type (NEED TO FIX)
            default:
                System.out.println("TASK TYPE ERROR!");
        }

        if (task != null) {
            if (details[1].equals("1")) {
                task.markAsDone();
            }
        }

        return task;
    }

}
