import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = parseLineToTask(line); // Use this class's method
                    if (task != null) {
                        loadedTasks.add(task);
                    }
                }
            } catch (IOException e) {
                System.out.println("Unable to read tasks from file: " + e.getMessage());
            }
        }
        return loadedTasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        }
    }

    // Moved to Storage class from TaskList
    private Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        Task task = null;

        try {
            switch (type) {
            case "T":
                task = new Todo(parts[2].trim());
                break;
            case "D":
                task = new DeadLine(parts[2].trim(), parts[3].trim());
                break;
            case "E":
                task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                break;
            }

            if (task != null) {
                boolean isDone = parts[1].trim().equals("1");
                task.setDone(isDone);
            }
        } catch (Exception e) {
            System.err.println("Failed to parse line into task: " + line);
        }

        return task;
    }
}

