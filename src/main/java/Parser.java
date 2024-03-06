
import jason.errorhandling.JasonException;

/**
 * The Parser class is responsible for interpreting and executing commands input by the user.
 * It parses the input string, determines the type of command, and delegates the command execution
 * to the appropriate method in the TaskList class or directly interacts with the UI and Storage classes.
 */
public class Parser {

    /**
     * Executes the command specified by the input string.
     * This method splits the input into parts to identify the command and its arguments, if any.
     * Based on the command, it calls the respective method in the TaskList or directly interacts
     * with the UI and Storage for specific operations.
     *
     * @param input     the full command line input by the user.
     * @param taskList  the TaskList instance for managing tasks.
     * @throws JasonException if any error occurs during command execution or data processing.
     */
    public static void executeCommand(String input, TaskList taskList) {
        String[] parts = input.split(" ", 2);

        try {
            if (parts[0].equalsIgnoreCase("mark")) {
                taskList.markTask(parts);
            } else if (parts[0].equalsIgnoreCase("unmark")) {
                taskList.unmarkTask(parts);
            } else if (parts[0].equalsIgnoreCase("list")) {
                Ui.showList(taskList.getTasks());
            } else if (parts[0].equalsIgnoreCase("delete")) {
                taskList.deleteTask(parts);
            } else {
                // For "todo", "deadline", "event" commands
                taskList.addTasks(input);
            }


            Storage.save(taskList.getTasks());
        } catch (JasonException e) {
            Ui.showError(e.getMessage());
        }
        Ui.showLine();
    }
}
