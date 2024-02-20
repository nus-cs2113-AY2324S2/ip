import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class DavinciFileHandler {

    public static List<String> readFile(String filePath) throws IOException {
        Path myPath = Paths.get(filePath);
        if (!Files.exists(myPath)) {
            new File(filePath);
        }
        return Files.readAllLines(myPath);
    }

    public static void appendLine(String filePath, String line) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(line + "\n");
        fw.close();
    }

    public static void writeFile(String filePath, List<Task> tasks) throws IOException {
        try {
            for (Task task : tasks) {
                appendLine(filePath, task.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error appending lines to file: " + e.getMessage());
            throw e;
        }
    }
}

