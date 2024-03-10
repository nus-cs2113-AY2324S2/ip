import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    private final String filePath;

    public TaskManager(String filePath) {
        this.filePath = filePath;
        tasks = new ArrayList<>();
        loadTasks(); // Load tasks from file when TaskManager is instantiated
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasksToFile(); // Save tasks to file after adding a new task
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    // Add other methods for managing tasks...

    private void loadTasks() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return; // If file doesn't exist, do nothing
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        String by = parts[3];
                        tasks.add(new Deadline(description, by, isDone));
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        tasks.add(new Event(description, from, to, isDone));
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private void saveTasksToFile() {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
