import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import jason.errorhandling.JasonException;
import jason.tasks.Task;
import jason.tasks.Todo;
import jason.tasks.Deadline;
import jason.tasks.Events;

/**
 * Handles loading and saving tasks to a file.
 * This class provides static methods to load tasks from a file into a TaskList and save tasks from a TaskList into a file.
 */
public class Storage {
    private static final String LIST_PATH = "jasonTaskList.txt";
    /**
     * Loads tasks from the specified path into the Task List.
     * It parses each line of the file to determine the type of task and creates corresponding Task objects to add to the Task List.
     *
     * @param taskList The TaskList instance to which the loaded tasks will be added.
     * @throws JasonException If there are issues accessing the file or parsing task data.
     */
    public static void load(TaskList taskList) throws JasonException {
        File file = new File(LIST_PATH);
        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with an empty task list.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = null;
                if (line.startsWith("[T]")) {
                    task = Todo.parseFromFile(line);
                } else if (line.startsWith("[D]")) {
                    task = Deadline.parseFromFile(line);
                } else if (line.startsWith("[E]")) {
                    task = Events.parseFromFile(line);
                }
                taskList.appendToList(task);



            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
    /**
     * Saves the tasks from the given task list to the file.
     * Each task is converted to a string representation and written to the file, one task per line.
     *
     * @param list The ArrayList of Task objects to be saved.
     * @throws JasonException If there are issues writing to the file.
     */
    public static void save(ArrayList<Task> list) throws JasonException {
        try (FileWriter writer = new FileWriter(LIST_PATH)) {
            for (Task task : list) {
                writer.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            throw new JasonException("Error occurred while saving tasks to file: " + e.getMessage());
        }
    }
}
