import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class DukeFile {
    private static final String FILENAME = "./dukeData.txt";
    private static void writeToFile(String filePath, String textToAdd, boolean ifAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, ifAppend);
        fw.write(textToAdd);
        fw.close();
    }
    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(FILENAME, inputText, ifAppend);
        } catch (IOException e) {
            PrintText.printWithLinebreak("Exceptions occurred");
        }
    }
    public static int latestIndex() {
        try {
            Scanner s = new Scanner(FILENAME);
            String lastLine = "";
            while (s.hasNext()) {
                lastLine = s.nextLine();
            }
            int index = Integer.parseInt(lastLine.split("\\.")[0]);
            return index;
        } catch (Exception e) {
            return 0;
        }
    }
    public static void main(String[] args) {
        File dukeData = new File(FILENAME);
        if (!dukeData.exists()) {
            try {
                writeToFile(dukeData.getPath(), "", true);
            } catch (IOException e) {
                PrintText.printWithLinebreak("File does not exist.");
            }
        }
    }
}
