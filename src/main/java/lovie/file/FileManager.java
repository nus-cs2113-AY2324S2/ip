package lovie.file;

import lovie.task.Deadline;
import lovie.task.Event;
import lovie.task.Task;
import lovie.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {
    private String filePath;

    public FileManager(String relativePath) {
        this.filePath = relativePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = parseTaskLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                newTask = new ToDo(taskDescription);

        }

        if (newTask != null && isDone) {
            newTask.markAsDone(); // Mark the task as done if indicated
        }

        return newTask;
    }

}
