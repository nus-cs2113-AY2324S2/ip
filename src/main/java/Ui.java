import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text Ui of the application.
 * Contains all the methods that directly interact with the user.
 */
public class Ui {

    public static final String LINE = "____________________________________________________________";
    public static final int OFFSET = 1;  //offset between 1-indexing and 0-indexing

    public static void GreetUser() throws IOException {
        System.out.println(LINE);
        System.out.println("Hello! I'm Katleen.");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
        Storage.loadFileContents();
    }

    public static void endConversation() {
        System.out.println(LINE);
        System.out.println("Bye, have a nice day!");
        System.out.println(LINE);
    }

    private final Scanner in;
    private final PrintStream out;

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    public Ui() {
        this(System.in, System.out);
    }

    public String getUserInput() {
        out.println("Enter command: ");
        return in.nextLine();
    }

    /**
     * Prints the tasks given in the array one by one
     * @param tasks the tasks to be printed
     */
    public static void printTasks(ArrayList<Task> tasks) {
        int taskNo = OFFSET;
        for (Task t : tasks) {
            System.out.print(taskNo + ". ");
            t.printTask();
            taskNo++;
        }
    }

}
