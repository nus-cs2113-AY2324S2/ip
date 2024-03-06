import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import jason.errorhandling.JasonException;
import jason.tasks.Task;
import jason.tasks.Todo;
import jason.tasks.Deadline;
import jason.tasks.Events;


public class Storage {
    private static final String LIST_PATH = "jasonTaskList.txt";

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
