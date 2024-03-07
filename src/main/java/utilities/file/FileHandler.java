package utilities.file;

import ui.Constants;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class FileHandler {

    /**
     * Reads existing file or creates new one when absent.
     *
     * @return lines read from file
     * @throws IOException When I/O error occurs.
     */
    protected static List<String> readFile() throws IOException {
        Path textFile = Paths.get(Constants.FILEPATH);
        createDirectories(textFile.getParent());
        if (!Files.exists(textFile)) {
            Files.createFile(textFile);
        }
        return Files.readAllLines(textFile);
    }

    /**
     * Writes tasks details to an existing file.
     *
     * @param entries Lines containing task details that will be written to the file.
     * @throws IOException When I/O error occurs.
     */
    protected static void writeFile(List<String> entries) throws IOException {
        Path textFile = Paths.get(Constants.FILEPATH);
        createDirectories(textFile.getParent());

        try (FileWriter fw = new FileWriter(Constants.FILEPATH)) {
            for (String entry : entries) {
                fw.write(entry + Constants.NEWLINE);
            }
        } catch (IOException e) {
            System.out.println(Constants.FILEWRITEERROR + e.getMessage());
            throw e;
        }
    }

    protected static void createDirectories(Path directory) throws IOException {
        try {
            Files.createDirectories(directory);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            System.out.println(Constants.CREATEDIRECTORYERROR + e.getMessage());
            throw e;
        }
    }
}
