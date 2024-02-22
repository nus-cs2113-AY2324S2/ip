import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class DukeFile {
    private static final String FILENAME = "src/main/java/dukeData.txt";
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    public static void updateFile(String inputText) {
        try {
            writeToFile(FILENAME, inputText);
        } catch (IOException e) {
            PrintText.printWithLinebreak("Exceptions occurred");
        }
    }

    public static void main(String[] args) {
        File dukeData = new File(FILENAME);
        try {
            writeToFile(dukeData.getPath(), "Initializing..");
        } catch (IOException e) {
            PrintText.printWithLinebreak("File does not exist.");
        }
    }
}
