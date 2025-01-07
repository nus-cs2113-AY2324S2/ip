/**
 * CommandParser is responsible for interpreting and executing commands
 * input by the user. It acts as a controller that directs user commands
 * to appropriate actions on the task list.
 */
public class CommandParser {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a CommandParser with the specified task list and UI.
     *
     * @param taskList the task list to be manipulated based on user commands.
     * @param ui the UI object for interacting with the user.
     */
    public CommandParser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Parses and executes a user input command. Supported commands include
     * adding, deleting, marking, unmarking tasks, and finding tasks by keywords.
     * It also handles listing all tasks and exiting the application.
     *
     * @param userInput the full user input command to be parsed and executed.
     */
    public void parseCommand(String userInput) {
        try {
            if (userInput.trim().isEmpty()) {
                ui.showError("The input cannot be empty!");
            } else if (userInput.equalsIgnoreCase("list")) {
                taskList.listTasks();
            } else if (userInput.startsWith("delete")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1; // Get task index
                taskList.deleteTask(taskIndex);
            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1; // Get task index
                taskList.markTaskAsDone(taskIndex);
            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1; // Get task index
                taskList.markTaskAsNotDone(taskIndex);
            } else if (userInput.startsWith("find")) {
                String keyword = userInput.substring(5); // Assume "find " is followed by a keyword
                taskList.findTask(keyword);
            } else if (!userInput.startsWith("todo") && !userInput.startsWith("deadline")
                    && !userInput.startsWith("event") && !userInput.startsWith("find")) {
                ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } else {
                taskList.addTask(userInput);
            }
        } catch (HandleException he) {
            ui.showError(he.getMessage());
        } catch (NumberFormatException nfe) {
            ui.showError("OOPS!!! The task number is invalid.");
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            ui.showError("OOPS!!! It seems like the command is not complete.");
        }
    }
}
