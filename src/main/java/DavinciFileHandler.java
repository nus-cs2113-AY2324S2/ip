import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

/**
 * Handles file operations such as reading and writing tasks to a file, and creating directories.
 */
public class DavinciFileHandler {

    public static final String ERROR_CREATING_DIRECTORIES = "Error creating directories: ";
    public static final String ERROR_WRITING_FILE = "Error writing file: ";
    public static final String WRITING_TO_FILE = "Writing to file: ";
    public static final String ERROR_CREATING_FILE = "Error creating file: ";
    public static final String CREATING_FILE = "Creating file: ";
    public static final String LOADING_PAST_TASKS_FROM = "Loading past tasks from ";

    /**
     * Reads the contents of a file and returns them as a list of strings.
     *
     * @param filePath The path to the file to be read.
     * @return A list of strings representing the lines of the file.
     * @throws IOException If an I/O error occurs.
     */
    public static List<String> readFile(String filePath) throws IOException {
        Path myPath = Paths.get(filePath);
        Ui.printMessage(LOADING_PAST_TASKS_FROM + filePath);
        createDirectories(myPath.getParent());

        if (!Files.exists(myPath)) {
            try {
                System.out.println(CREATING_FILE + filePath);
                Files.createFile(myPath);
            } catch (IOException e) {
                System.out.println(ERROR_CREATING_FILE + e.getMessage());
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
            System.out.println(WRITING_TO_FILE + filePath);
            Files.write(myPath, lines);
        } catch (IOException e) {
            System.out.println(ERROR_WRITING_FILE + e.getMessage());
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
            System.out.println(ERROR_CREATING_DIRECTORIES + e.getMessage());
            throw e;
        }
    }
}
