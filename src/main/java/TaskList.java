import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> tasks;
    private static final String FILE_PATH = "./data/duke.txt";

    public TaskList() {
        this.tasks = new ArrayList<>();
        loadTasks(); // Ensure tasks are loaded when an instance is created
    }

    private void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // Skip loading if file does not exist
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseLineToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) { // Catching IOException which covers FileNotFoundException
            System.out.println("Unable to read tasks from file: " + e.getMessage());
        }
    }

    private Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;

        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = parts.length > 3 ? parts[3] : "";
            task = new DeadLine(description, by);
            break;
        case "E":
            String from = parts.length > 3 ? parts[3] : "";
            String to = parts.length > 4 ? parts[4] : "";
            task = new Event(description, from, to);
            break;
        default:
            System.out.println("Unknown task type in file: " + type);
            return null; // Return null for unknown task types
        }
        task.isDone = isDone;
        return task;
    }




    public void addTask(String userInput) throws HandleException {
        Task task = TaskFactory.createTask(userInput);
        if (task != null) {
            tasks.add(task);
            saveTasks(); // Save the task list to file after adding a new task
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            if (!file.getParentFile().mkdirs() && !file.getParentFile().exists()) {
                System.out.println("Failed to create directories for the task file.");
                return;
            }
            try (FileWriter writer = new FileWriter(file, false)) {
                for (Task task : tasks) {
                    writer.write(task.toFileFormat() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to save tasks to file: " + e.getMessage());
        }
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}

