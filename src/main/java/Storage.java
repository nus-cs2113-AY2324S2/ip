import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public static List<Task> loadTasksFromFile(String filePath) throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
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
                    default:
                        break;
                }
            }
            scanner.close();
        }
        return tasks;
    }

    public static void saveTasksToFile(List<Task> tasks, String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create parent directories if they don't exist
        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            writer.write(task.toFileString() + "\n");
        }
        writer.close();
    }
}
