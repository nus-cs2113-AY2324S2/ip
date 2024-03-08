package lovie.file;

import lovie.task.TaskList;
import lovie.task.Task;
import lovie.task.Deadline;
import lovie.task.Event;
import lovie.task.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents the storage of the tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param relativePath The relative path of the file.
     */
    public Storage(String relativePath) {
        this.filePath = relativePath;
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The list of tasks.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = parseTaskLine(line);
                    if (task != null) {
                        tasks.addTask(task);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tasks;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks.
     */
    public void saveTasks(TaskList tasks) {
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (int i = 0; i < tasks.getSize(); i += 1) {
                writer.write(tasks.get(i).toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the task line from the file.
     *
     * @param line The line from the file.
     * @return The task parsed from the line.
     */
    private Task parseTaskLine(String line) {
        if (line.isEmpty()) {
            return null;
        }

        String[] parts = line.split(" \\| ");

        String taskDescription = parts[0];
        boolean isDone = "1".equals(parts[1].trim());
        String taskType = taskDescription.split(" ")[0];
        Task newTask;

        switch (taskType) {
            case "event":
                newTask = new Event(taskDescription);
                break;
            case "deadline":
                newTask = new Deadline(taskDescription);
                break;
            default:
                newTask = new Todo(taskDescription);

        }

        if (newTask != null && isDone) {
            newTask.markAsDone(); // Mark the task as done if indicated
        }

        return newTask;
    }

}
