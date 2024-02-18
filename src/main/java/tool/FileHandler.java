package tool;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.util.Scanner;
import java.util.List;
import java.util.regex.Pattern;

public class FileHandler {
    public static List<String> readFile(String filePath) throws IOException {
        Path myPath = Paths.get(filePath);
        if (!Files.exists(myPath)) {
            new File(filePath);
        }
        return Files.readAllLines(myPath);
    }

    public static void writeFile(String filePath, String tempPath, List<String> lines) throws IOException {
        File temp = new File(tempPath);
        for (String line : lines) {
            appendLine(tempPath, line);
        }
        Files.copy(Paths.get(tempPath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(tempPath));
    }

    public static void appendLine(String filePath, String line) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(line + "\n");
        fw.close();
    }
}
