import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * prints TaskList.txt file contents
 */
public class ReadFileContents {
    private static Ui ui;

    private static void printFileContents (String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner taskString = new Scanner(file); //reads tasks from TaskList.txt file
        while (taskString.hasNext()) {
            System.out.println(taskString.nextLine());
        }
    }

    public static void main(String[] args) {
        try {
            printFileContents("TaskList.txt");
        } catch (FileNotFoundException e) {
            new CreateFile();
            String errorDescription = "File not found";
            ui.errorMessage(errorDescription);
        }
    }
}