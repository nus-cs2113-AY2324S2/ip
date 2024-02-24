import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class DavinciFileHandler {

    /**
     * Reads the contents of a file and returns them as a list of strings.
     *
     * @param filePath The path to the file to be read.
     * @return A list of strings representing the lines of the file.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Writes a list of strings to a file.
     *
     * @param filePath The path to the file to be written.
     * @param lines The list of strings to be written to the file.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Creates directories for a given path if they do not exist.
     *
     * @param directory The path to the directory to be created.
     * @throws IOException If an I/O error occurs.
     */
    public static void createDirectories(Path directory) throws IOException {
        try {
            Files.createDirectories(directory);
        } catch (FileAlreadyExistsException ignored) { // ignore if the file exists
        } catch (IOException e) {
            System.out.println("Error creating directories: " + e.getMessage());
            throw e;
        }
    }
}
