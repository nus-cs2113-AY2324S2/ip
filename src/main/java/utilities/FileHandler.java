package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class FileHandler {
    public static List<String> readFile(String filePath) throws IOException {
        Path textFile = Paths.get(filePath);
        createDirectories(textFile.getParent());
        if (!Files.exists(textFile)) {
            Files.createFile(textFile);
        }
        return Files.readAllLines(textFile);
    }

    public static void writeFile(String filePath, List<String> entries) throws IOException {
        Path textFile = Paths.get(filePath);
        createDirectories(textFile.getParent());

        try (FileWriter fw = new FileWriter(filePath)) {
            for (String entry : entries) {
                fw.write(entry + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to write file: " + e.getMessage());
            throw e;
        }
    }

    public static void createDirectories(Path directory) throws IOException {
        try {
            Files.createDirectories(directory);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            System.out.println("Unable to create directory: " + e.getMessage());
            throw e;
        }
    }
}
