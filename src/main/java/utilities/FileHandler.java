package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

public class FileHandler {
    public static List<String> readFile(String filePath) throws IOException {
        Path textFile = Paths.get(filePath);
        if (!Files.exists(textFile)) {
            new File(filePath);
        }
        return Files.readAllLines(textFile);
    }

    public static void writeFile(String filePath, List<String> entries) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (String entry : entries) {
                fw.write(entry + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to write file: " + e.getMessage());
            throw e;
        }
    }
}
