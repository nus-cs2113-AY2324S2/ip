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
        String[] list = new String[100];
        int index = 0;
        while (!isExit) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                input.close();
                isExit = true;
                printExitMessage();
            } else if (userInput.equals("list")) {
                printLine();
                for (int i = 0; i < index; i++) {
                    System.out.println("    " + (i + 1) + ". " + list[i]);
                }
                printLine();
            } else {
                list[index++] = userInput;
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
