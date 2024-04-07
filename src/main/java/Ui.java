import java.util.Scanner;

/**
 * The Ui class handles the user interface of the Quokka task management program.
 * It interacts with the user, reads user input, and executes corresponding actions.
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Starts the Quokka program by displaying a welcome message and handling user input.
     *
     * @param taskList The task list used in the program.
     */
    public static void start(TaskList taskList) {
        System.out.println("Hello! I'm Quokka");
        System.out.println("What can I do for you?");
        handleUserInput(taskList);
    }

    /**
     * Handles user input and executes corresponding actions based on the input.
     *
     * @param taskList The task list used in the program.
     */
    private static void handleUserInput(TaskList taskList) {
        while (true) {

            // Read user input
            String userInput = scanner.nextLine();
            String filteredInput = userInput.toLowerCase();
            String[] parts = filteredInput.split(" ");

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                Storage.saveTasksToFile(taskList); // Save tasks whenever task list changes
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            }

            switch (parts[0]) {
            case "list":
                taskList.displayTasks();
                break;
            case "mark":
                taskList.markTaskAsDone(filteredInput);
                break;
            case "unmark":
                taskList.markTaskAsNotDone(filteredInput);
                break;
            case "todo":
                taskList.addTask(Parser.parseTodoTask(filteredInput));
                break;
            case "deadline":
                taskList.addTask(Parser.parseDeadlineTask(filteredInput));
                break;
            case "event":
                taskList.addTask(Parser.parseEventTask(filteredInput));
                break;
            case "find":
                try {
                    String keyword = parts[1];
                    taskList.findTask(keyword);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Invalid format inputted.");
                }
                break;
            case "delete":
                if (parts.length == 2) {
                        try {
                            int taskIndex = Integer.parseInt(parts[1]);
                            taskList.deleteTask(taskIndex);
                        } catch (NumberFormatException e) {
                            System.out.println("    Invalid task index format");
                        }
                } else {
                        System.out.println("    Please provide a valid task index to delete");
                }
                break;
            default:
                System.out.println("    I'm sorry, I don't understand that command.");
            }
        }
    }
}
