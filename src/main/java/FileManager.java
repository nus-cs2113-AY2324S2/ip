import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String filePath;

    public FileManager(String relativePath) {
        this.filePath = System.getProperty("user.home") + File.separator + String.join(File.separator, relativePath.split("/"));
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
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

    public void saveTasks(List<Task> tasks) {
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

        char taskType = line.charAt(0);
        switch (taskType) {
            case 'T':
                return new ToDo(line);
            case 'D':
                return new Deadline(line);
            case 'E':
                return new Event(line);
            default:
                return null;
        }
    }
}
