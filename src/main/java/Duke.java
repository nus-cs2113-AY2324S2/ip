import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * lists tasks saved in the local TaskList.txt file
     * @param filePath the path to the local TaskList.txt file
     * @throws FileNotFoundException exception when local TaskList.txt file does not exist
     */
    private static void printFileContents (String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static Ui ui;
    public static void main (String[] args) throws UnexpectedCommandException, EmptyLineException, IOException, FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String errorDescription;
        int index = 0;//number of items in the list
        String line = " ";
        File file = new File("TaskList.txt");

        ui = new Ui();
        ui.sayHi();
        try {
            System.out.println("Here are the items in your task list: ");
            printFileContents("TaskList.txt");
        } catch (FileNotFoundException e) {
            errorDescription = "File not found";
            ui.errorMessage(errorDescription);
        }

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    errorDescription = "File creation failed.";
                    ui.errorMessage(errorDescription);
                }
            } catch (IOException e) {
                errorDescription = "An I/O error occurred.";
                ui.errorMessage(errorDescription);
                e.printStackTrace();
            }
        } else {
            System.out.println("File already exists: " + file.getAbsolutePath());
        }

        new Parser(tasks, index, line);
    }
}
