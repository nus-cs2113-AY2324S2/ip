package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.EmptyTaskException;
import exceptions.MissingDeadlineException;
import exceptions.MissingStartException;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * Represents a utility class used to load tasks from a file.
 * This class extends the Storage class and provides methods to load tasks from a specified file.
 */
public class LoadData extends Storage {
    public static ArrayList<Task> savedTasks = new ArrayList<>(100);

    /**
     * Constructs a LoadData object with the specified file path.
     *
     * @param filepath The path to the file from which tasks will be loaded
     */
    public LoadData(String filepath) {
        super(filepath);
    }

    /**
     * Loads tasks from the specified file.
     *
     * @param filepath The path to the file from which tasks will be loaded
     * @return The ArrayList of tasks loaded from the file
     * @throws FileNotFoundException If the specified file is not found
     */
    public static ArrayList<Task> loadData(String filepath) throws FileNotFoundException {
        File data = new File(filepath);
        Scanner input = new Scanner(data);
        int count = 0;
        do {
            String task = input.nextLine();
            count++;
            char type = task.charAt(0);
            String description = task.substring(8);
            boolean isCompleted = task.charAt(4) != '0';
            String[] params = description.split("\\|");
            if (type == 'T') {
                savedTasks.add(new ToDo(description));
            } else if (type == 'D') {
                String taskDescription = params[0].trim();
                String deadline = params[1].trim();
                savedTasks.add(new Deadline(taskDescription, deadline));
            } else if (type == 'E') {
                String taskDescription = params[0].trim();
                String from = params[1].trim();
                String by = params[2].trim();
                savedTasks.add(new Event(taskDescription, from, by));
            } else {
                break;
            }
            int index = savedTasks.size() - 1;
            savedTasks.get(index).setDone(isCompleted);
        } while (input.hasNext());
        return savedTasks;
    }
}