import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void main(String[] args) throws UnexpectedCommandException, EmptyLineException, IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        //greeting
        System.out.println("Hello! I'm Apple");
        System.out.println("What can I do for you?");
        try {
            System.out.println("Here are the items in your task list: ");
            printFileContents("TaskList.txt");

        } catch (FileNotFoundException e) {
            File f = new File("TaskList.txt");
            System.out.println("File not found");
            System.out.println("File created: " + f.getAbsolutePath());
        }
        int index = 0;//number of items in the list
        String line = " ";

        new ManageInputs(tasks, index, line);
    }
}
