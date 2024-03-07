package Yoj;
import java.io.FileWriter;
import java.io.IOException;

public class YojFile {
    private static final String FILE_PATH = "src/data/Yojdata.txt";
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String FILE_PATH, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}

