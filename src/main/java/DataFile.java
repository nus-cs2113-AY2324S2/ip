import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class DataFile {
    File data = new File("data/tasks.txt");
    protected void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.data);
        fw.write(textToAdd);
        fw.close();
    }

    protected void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.data, true);
        fw.write(textToAdd);
        fw.close();
    }

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
