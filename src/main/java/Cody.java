import java.util.Scanner;

public class Cody {

    // Constants for repeated strings
    private static final String BORDER = "_______________________________________________________________\n";
    private static final String GREET_MESSAGE = "Hey there! I'm Cody, your personal task manager\n"
            + "Tell me your tasks and I will create a task list for you\n";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";

    private static void printMessage(String message) {
        System.out.println(BORDER + message + BORDER);
    }

    // greets user
    private static void greet() {
        printMessage(GREET_MESSAGE);
    }

    // exits the program
    private static void exit() {
        printMessage(EXIT_MESSAGE);
    }

    public static void main(String[] args) {
        greet();
        TaskManager taskManager = new TaskManager(); // Assuming TaskManager handles its tasks
        exit();
    }
}


