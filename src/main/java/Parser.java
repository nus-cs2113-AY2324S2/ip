import java.util.Scanner;


/**
 * The Parser class is responsible for interpreting user input commands and
 * executing the corresponding actions within the task management application.
 */

public class Parser {
    private final Scanner scanner;
    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Continuously reads and processes user commands until the exit command is received.
     * Recognizes and executes a range of commands related to task management.
     */
    public void parseCommands() {
        String userCommand;

        while (true) {
            System.out.print("Enter a command: ");
            userCommand = scanner.nextLine().trim();

            if (userCommand.equalsIgnoreCase("thank you and bye")) {
                break;
            }
            processCommand(userCommand);
        }
        scanner.close();
    }

    /**
     * Processes an individual user command by matching it against known commands
     * and executing the corresponding actions.
     *
     * @param userCommand The user command to be processed.
     */

    private void processCommand(String userCommand) {
        if (userCommand.equalsIgnoreCase("hi")) {
            System.out.println("HEY HEY YOU YOU I CAN BE YOUR... task manager bot");
        } else if (userCommand.equalsIgnoreCase("bye")) {
            System.out.println("Bro. Say 'thank you and bye'. If not i'm not letting you exit~~");
        } else if (userCommand.equalsIgnoreCase("list")) {
            TaskList.displayTasks();
        } else if (userCommand.startsWith("mark")) {
            TaskList.markTask(userCommand);
        } else if (userCommand.startsWith("unmark")) {
            TaskList.unmarkTask(userCommand);
        } else if (userCommand.startsWith("todo")) {
            TaskList.addTodoTask(userCommand);
        } else if (userCommand.startsWith("deadline")) {
            TaskList.addDeadline(userCommand);
        } else if (userCommand.startsWith("event")) {
            TaskList.addEvent(userCommand);
        } else if (userCommand.startsWith("delete")) {
            TaskList.deleteTask(userCommand);
        } else if (userCommand.startsWith("find ")) {
            String keyword = userCommand.substring(5);
            TaskList.findTask(keyword);
        } else {
            System.out.println("bruh. do better, read instructions.");
        }
    }
}