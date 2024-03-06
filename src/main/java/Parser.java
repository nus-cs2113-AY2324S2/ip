import java.util.Scanner;

public class Parser {
    private final Scanner scanner;

    public Parser(Zap zap) {
        scanner = new Scanner(System.in);
    }

    public void parseCommands() {
        String userCommand;

        while (true) {
            System.out.print("Enter a command: ");
            userCommand = scanner.nextLine().trim();

            if (userCommand.equalsIgnoreCase("thank you and bye")) {
                break; // Exit the loop, signaling to end the program
            }

            processCommand(userCommand);
        }

        scanner.close();
    }

    private void processCommand(String userCommand) {
        if (userCommand.equalsIgnoreCase("hi")) {
            System.out.println("Hello! I am ZAP and I am at your service!");
        } else if (userCommand.equalsIgnoreCase("bye")) {
            System.out.println("You should say thank you, then say bye.");
        } else if (userCommand.equalsIgnoreCase("list")) {
            Zap.displayTasks();
        } else if (userCommand.startsWith("mark")) {
            Zap.markTask(userCommand);
        } else if (userCommand.startsWith("unmark")) {
            Zap.unmarkTask(userCommand);
        } else if (userCommand.startsWith("todo")) {
            Zap.addTodoTask(userCommand);
        } else if (userCommand.startsWith("deadline")) {
            Zap.addDeadline(userCommand);
        } else if (userCommand.startsWith("event")) {
            Zap.addEvent(userCommand);
        } else if (userCommand.startsWith("delete")) {
            Zap.deleteTask(userCommand);
        } else {
            System.out.println("proper english pls. don't waste time already >:(");
        }
    }
}
