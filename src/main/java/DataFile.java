import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class DataFile {
    File data = new File("tasks.txt");

    /**
     * Writes a String to the data File
     * @param textToAdd text to be written
     * @throws IOException exception in case writing fails
     */
    protected void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.data);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends text to the end of the data file
     * @param textToAdd text to be appended
     * @throws IOException exception in case appending fails
     */
    protected void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.data, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends text at the start of the data file
     * @param textToAdd text to be appended
     * @throws IOException exception in case appending fails
     */
    protected void appendAtFirstLine(String textToAdd) throws IOException {
        String temp = "";
        Scanner scanner = new Scanner(data);
        scanner.nextLine();
        if (scanner.hasNext()) {
            temp = scanner.useDelimiter("\\A").next();
        }
        writeToFile(textToAdd);
        appendToFile(temp);
    }
}
