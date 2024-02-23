import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import command.UserInput;
import task.Task;

public class John {

    private static List<Task> taskList = new ArrayList<>();
    public static final String LINE_BREAK = "----------------------";

    public static void main(String[] args) {

        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        System.out.println(LINE_BREAK);
        String userInput = in.next();

        while (!userInput.equalsIgnoreCase("bye")) {
            UserInput.parseInput(userInput, in, taskList);
            userInput = in.next();
            System.out.println(LINE_BREAK);
        }

        printExitMessage();

    }

    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello! I'm John Chadbot.\n" + "What can I do for you?\n");
    }
}
