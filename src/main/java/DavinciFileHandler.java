import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class DavinciFileHandler {

    public static List<String> readFile(String filePath) throws IOException {
        Path myPath = Paths.get(filePath);
        Ui.printMessage("Loading past tasks from " + filePath);
        createDirectories(myPath.getParent());

        if (!Files.exists(myPath)) {
            try {
                System.out.println("Creating file: " + filePath);
                Files.createFile(myPath);
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
                throw e;
            }
        }
        return Files.readAllLines(myPath);
    }

    public static void writeFile(String filePath, List<String> lines) throws IOException {
        Path myPath = Paths.get(filePath);
        createDirectories(myPath.getParent());

        try {
            System.out.println("Writing to file: " + filePath);
            Files.write(myPath, lines);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            throw e;
        }
    }

    public static void createDirectories(Path directory) throws IOException {
        try {
            Files.createDirectories(directory);
        } catch (FileAlreadyExistsException ignored) { //ignore if the file exists
        } catch (IOException e) {
            System.out.println("Error creating directories: " + e.getMessage());
            throw e;
        }
    }
}
