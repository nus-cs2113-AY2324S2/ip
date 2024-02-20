import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Save {

    protected static final Path FILE_PATH = Path.of("./data/duck.txt");

    public static void createNewFile() throws IOException {

        if (!Files.isDirectory(FILE_PATH.getParent())) {
            Files.createDirectories(FILE_PATH.getParent());
        } else {
            System.out.println("dir exists");
        }
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
            System.out.println("File name: " + FILE_PATH.getFileName());
            System.out.println("Parent directory: " + FILE_PATH.getParent());
            System.out.println("Absolute path: " + FILE_PATH.toAbsolutePath());
        } else {
            System.out.println("file exists");
        }
    }
}
