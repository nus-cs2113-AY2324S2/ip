import java.util.Scanner;

public class Duke {

    private static final String NAME = "Natsu";

    private static void printGreeting() {
        printLine();
        System.out.println("    Hello! I'm " + NAME);
        System.out.println("    What can I do for you?");
        printLine();
    }

    private static void echoCommands() {
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                input.close();
                isExit = true;
                printExitMessage();
            } else {
                printLine();
                System.out.println("    " + userInput);
                printLine();
            }
        }
    }

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private static void printExitMessage() {
        printLine();
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        printGreeting();
        echoCommands();
    }
}
