/**
 * The CommandProcessor class handles the processing and execution of user commands.
 */
public class CommandProcessor {
    private TaskManager taskManager;

    /**
     * Constructs a CommandProcessor object and initializes a TaskManager.
     */
    public CommandProcessor() {
        this.taskManager = new TaskManager();
    }

    /**
     * Processes and executes the command entered by the user.
     *
     * @param userInput The command entered by the user
     */
    public void processUserInput(String userInput) {
        String[] parts = userInput.split(" ", 2); // Split command and argument
        String command = parts[0].trim().toLowerCase();
        String argument = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
            case "find":
                taskManager.findTaskByKeyword(argument);
                break;
            case "todo":
                if (argument.isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    taskManager.addTodoTask(argument);
                }
                break;
            case "deadline":
                if (argument.isEmpty() || !argument.contains("/by")) {
                    System.out.println("OOPS!!! Invalid deadline format. Use: deadline <description> /by <deadline>");
                } else {
                    taskManager.addDeadlineTask(argument);
                }
                break;
            case "event":
                if (argument.isEmpty() || !argument.contains("/from") || !argument.contains("/to")) {
                    System.out.println("OOPS!!! Invalid event format. Use: event <description> /from <start time> /to <end time>");
                } else {
                    taskManager.addEventTask(argument);
                }
                break;
            case "mark":
                if (argument.isEmpty()) {
                    System.out.println("OOPS!!! Please provide the task number to mark.");
                } else {
                    taskManager.markTask(argument);
                }
                break;
            case "unmark":
                if (argument.isEmpty()) {
                    System.out.println("OOPS!!! Please provide the task number to unmark.");
                } else {
                    taskManager.unmarkTask(argument);
                }
                break;
            case "delete":
                if (argument.isEmpty()) {
                    System.out.println("OOPS!!! Please provide the task number to delete.");
                } else {
                    taskManager.deleteTask(argument);
                }
                break;
            case "list":
                taskManager.displayTaskList();
                break;
            default:
                System.out.println("That is not a valid command.");
        }
    }
}
