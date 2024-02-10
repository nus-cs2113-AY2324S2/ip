import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class UserInterface {

    private static final Logger LOGGER = Logger.getLogger(Nick.class.getName());

    public UserInterface() {

    }

    public void printByeMsg() {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void printAddTaskMsg(String taskName, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t" + taskName);
        System.out.println("\t" + "Now you have " + (taskCount + 1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printIntroName() {
        try {
            String name = new String(Files.readAllBytes(Paths.get("name.txt")));
            System.out.print(name);
        } catch (IOException exception) {
            LOGGER.severe(exception.toString());
        }
    }

    public void printIntroMsg() {
        printIntroName();
        System.out.println("____________________________________________________________");
        System.out.println("Welcome to the Ultimate Nick Bot!");
        System.out.println("What can I do for you?\n");
    }
}
