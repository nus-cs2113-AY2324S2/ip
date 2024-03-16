import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class TaskManager {
    private final List<Task> tasks;
    private final String filePath;

    public TaskManager(String filePath) {
        this.filePath = filePath;
        tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTodoTask(String description) {
        tasks.add(new TodoTask(description));
        saveTasksToFile();
    }

    public void addDeadlineTask(String description, String by) {
        tasks.add(new DeadlineTask(description, by));
        saveTasksToFile();
    }

    public void addEventTask(String description, String from, String to) {
        tasks.add(new EventTask(description, from, to));
        saveTasksToFile();
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasksToFile();
        }
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private void loadTasks() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
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
                        tasks.add(new TodoTask(description, isDone));
                        break;
                    case "D":
                        String by = parts[3];
                        tasks.add(new DeadlineTask(description, by, isDone));
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        tasks.add(new EventTask(description, from, to, isDone));
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
