import java.util.Scanner;

public class Natsu {

    private static final String NAME = "Natsu";
    private static final Task[] list = new Task[100];
    private static int taskCount = 0;

    private static void printGreeting() {
        printLine();
        System.out.println("     Hello! I'm " + NAME);
        System.out.println("     What can I do for you?");
        printLine();
    }

    private static void echoCommands() {
        try (Scanner input = new Scanner(System.in)) {
            boolean isActive = true;
            while (isActive) {
                String userInput = input.nextLine();
                isActive = processInput(userInput);
            }
        }
    }

    private static boolean processInput(String userInput) {
        switch (userInput) {
        case "bye":
            printExitMessage();
            return false;
        case "list":
            listTasks();
            break;
        default:
            handleTaskCommands(userInput);
        }
        return true;
    }

    private static void handleTaskCommands(String userInput) {
        if (userInput.startsWith("mark ")) {
            int itemIndex = Integer.parseInt(userInput.substring(5)) - 1;
            list[itemIndex].markAsDone();
            printLine();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + list[itemIndex].toString());
            printLine();
        } else if (userInput.startsWith("unmark ")) {
            int itemIndex = Integer.parseInt(userInput.substring(7)) - 1;
            list[itemIndex].markAsUndone();
            printLine();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + list[itemIndex].toString());
            printLine();
        } else {
            list[taskCount++] = new Task(userInput);
            printLine();
            System.out.println("     " + userInput);
            printLine();
        }
    }

    private static void listTasks() {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + list[i].toString());
        }
        printLine();
    }

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private static void printExitMessage() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        printGreeting();
        echoCommands();
    }
}
