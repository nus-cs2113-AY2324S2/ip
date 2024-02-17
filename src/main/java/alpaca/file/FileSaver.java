package alpaca.file;

import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void startFileWriter(String taskList) {
        String filePath = "data/Alpaca.txt";
        try {
            writeToFile(filePath, taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
