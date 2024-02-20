import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import commands.Task;
import commands.Deadline;
import commands.Event;
import commands.Todo;

public class DataManager {

    private static void writeToFile(String filePath, String data) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, data.getBytes());
    }

    public static void saveData(List<Task> list) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task t : list) {
            stringBuilder.append(t.toString()).append("\n");
        }
        writeToFile("src/main/java/data.txt", stringBuilder.toString());
    }

    public static List<Task> readData(String filePath) throws IOException{
        List<Task> list = new ArrayList<>();
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                if (!line.equals("")) {
                    list.add(parseLine(line));
                }
            }
            return list;
        } else {
            return list;
        }
    }

    private static Task parseLine(String str) {
        String brackets = str.substring(0, 7);
        String remaining = str.substring(7);

        // Extract task properties
        String type = brackets.substring(1, 2); // Assuming the type is always one character enclosed in square brackets
        boolean isDone = brackets.substring(3, 6).equals("[X]");
        String description = "";

        // Create Task object based on type
        switch (type) {
            case "T":
                description = remaining;
                return new Todo(description, isDone);
            case "D":
                description = remaining.substring(0, remaining.indexOf(" (by:"));
                String date = remaining.substring(remaining.indexOf("by:") + 3, remaining.length() - 1);
                return new Deadline(description, isDone, date);
            case "E":
                description = remaining.substring(0, remaining.indexOf(" (from:"));
                String from = remaining.substring(remaining.indexOf("from:")+5, remaining.indexOf(" to:"));
                String to = remaining.substring(remaining.indexOf("to:")+3, remaining.length() - 1);
                return new Event(description, isDone, from, to);
            default:
                System.err.println("Unknown task type: " + type);
                return null;
        }
    }
}
