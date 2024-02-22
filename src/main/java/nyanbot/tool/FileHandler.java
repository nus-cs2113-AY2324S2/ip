package nyanbot.tool;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileHandler {
    public static List<String> readFile(String dataPathString) throws IOException {
        Path dataPath = Paths.get(dataPathString);
        Path dataDirectory = dataPath.getParent();
        if (!Files.exists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
            Printer.printDirectoryCreated();
        }
        if (!Files.exists(dataPath)) {
            Files.createFile(dataPath);
            Printer.printFileCreated();
        }
        return Files.readAllLines(dataPath);
    }

    public static void writeFile(String dataPathString, List<String> lines) throws IOException {
        Path dataPath = Paths.get(dataPathString);
        Path dataDirectory = dataPath.getParent();
        String tempPathString = dataDirectory + "/temp.txt";
        Path tempPath = Paths.get(tempPathString);

        File temp = new File(tempPathString);
        temp.createNewFile();
        for (String line : lines) {
            appendLine(tempPathString, line);
        }
        Files.copy(tempPath, dataPath, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tempPath);
    }

    public static void appendLine(String directoryPath, String line) throws IOException {
        FileWriter fw = new FileWriter(directoryPath, true);
        fw.write(line + "\n");
        fw.close();
    }
}
