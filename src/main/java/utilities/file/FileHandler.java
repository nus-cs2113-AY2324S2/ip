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
    protected static List<String> readFile() throws IOException {
        Path textFile = Paths.get(Constants.FILEPATH);
        createDirectories(textFile.getParent());
        if (!Files.exists(textFile)) {
            Files.createFile(textFile);
        }
        return Files.readAllLines(textFile);
    }

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
