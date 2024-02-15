import java.util.Scanner;

public class Baron {
    public static final String EXIT_COMMAND = "bye";
    public static void main(String[] args) {
        greetUser();
        getUserInput();
        printExitMessage();
    }

    private static void greetUser() {
        System.out.println("Hello! I'm Baron");
        System.out.println("What can I do for you?\n");
    }

    public static void getUserInput() {
        Scanner userInput = new Scanner(System.in);
        TaskList tasks = new TaskList();
        while (true) {
            String input = userInput.nextLine();
            input = input.toLowerCase();
            if (input.equals(EXIT_COMMAND)) {
                break;
            } else if (input.isEmpty()) {
                System.out.println("Command cannot be empty. Please enter a valid command.");
                continue;
            }
            Parser.parseInput(input, tasks);
        }
    }

    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}