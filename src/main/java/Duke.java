import java.util.Scanner;

public class Duke {

    private static final String NAME = "Natsu";

    private static void printGreeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm " + NAME);
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
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
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + userInput);
            System.out.println("    ____________________________________________________________");
            }
        }
    }

    private static void printExitMessage() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        printGreeting();
        echoCommands();
    }
}
