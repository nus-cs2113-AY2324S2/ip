import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public static void loadTasksFromFile(String filePath, TaskList taskList) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            java.util.Scanner scanner = new java.util.Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                switch (type) {
                    case "T":
                        taskList.addTask(new Todo(description, isDone));
                        break;
                    case "D":
                        String by = parts[3];
                        taskList.addTask(new Deadline(description, by, isDone));
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        taskList.addTask(new Event(description, from, to, isDone));
                        break;
                    default:
                        break;
                }
            }
            scanner.close();
        }
    }

    public static void saveTasksToFile(TaskList taskList, String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create parent directories if they don't exist
        FileWriter writer = new FileWriter(file);
        for (Task task : taskList.getAllTasks()) {
            writer.write(task.toFileString() + "\n");
        }
        writer.close();
    }
}
