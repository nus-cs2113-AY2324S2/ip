import java.util.ArrayList;

/**
 * The Parser class is responsible for parsing user commands and executing corresponding actions
 * on the TaskList and Ui classes. It handles various commands such as adding tasks, marking tasks
 * as done, deleting tasks, listing tasks, and searching for tasks.
 */
public class Parser {
    /**
     * Parses the user command and executes the corresponding action.
     *
     * @param userCommand The user-entered command.
     * @param taskList    The TaskList object containing the list of tasks.
     * @param ui          The Ui object for displaying messages.
     */
    public static void parseUserCommand(String userCommand, TaskList taskList, Ui ui) {
        String[] parts = userCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        try {
            switch (commandWord) {
                case "bye":
                    ui.showByeMessage();
                    break;
                case "list":
                    ui.showTaskList(taskList.getTasks());
                    break;
                case "todo":
                    parseTodoCommand(parts, taskList, ui);
                    break;
                case "deadline":
                    parseDeadlineCommand(parts, taskList, ui);
                    break;
                case "event":
                    parseEventCommand(parts, taskList, ui);
                    break;
                case "mark":
                    parseMarkCommand(parts, taskList, ui);
                    break;
                case "unmark":
                    parseUnmarkCommand(parts, taskList, ui);
                    break;
                case "delete":
                    parseDeleteCommand(parts, taskList, ui);
                    break;
                case "find":
                    parseFindCommand(parts, taskList, ui);
                    break;
                default:
                    ui.showError("Invalid command. Please enter a valid command.");
            }
        } catch (JoeyException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Parses the "todo" command and adds a Todo task to the taskList.
     *
     * @param parts    The parts of the user command.
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui       The Ui object for displaying messages.
     * @throws JoeyException If the description for the todo task is empty.
     */
    private static void parseTodoCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        // Check if the user provided a description for the todo task
        if (parts.length < 2) {
            throw new JoeyException("Hey girlie, The description of a todo cannot be empty.");
        }

        // Extract the description from the user's input
        String description = parts[1].trim();
        taskList.addTask(new Todo(description, false));

        // Display a confirmation message using the Ui class
        ui.showTaskAdded(taskList.getTasks().get(taskList.getTasks().size() - 1), taskList.getTasks().size());
    }

    /**
     * Parses the "deadline" command and adds a Deadline task to the taskList.
     *
     * @param parts    The parts of the user command.
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui       The Ui object for displaying messages.
     * @throws JoeyException If the user does not provide enough information for the deadline task.
     */
    private static void parseDeadlineCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        // Check if the user provided enough information for the deadline task
        if (parts.length < 2) {
            throw new JoeyException("Hey girlie, Please provide both description and deadline for the deadline task.");
        }

        // Split the user's input to extract deadline details
        String[] deadlineParts = parts[1].split("/by", 2);

        // Check if both description and deadline are provided
        if (deadlineParts.length < 2) {
            throw new JoeyException("Hey girlie, Please provide both description and deadline for the deadline task.");
        }

        // Extract description
        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        // Add the deadline task to the taskList
        taskList.addTask(new Deadline(description, by, false));

        // Display a confirmation message using the Ui class
        ui.showTaskAdded(taskList.getTasks().get(taskList.getTasks().size() - 1), taskList.getTasks().size());
    }

    /**
     * Parses the "event" command and adds an Event task to the taskList.
     *
     * @param parts    The parts of the user command.
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui       The Ui object for displaying messages.
     * @throws JoeyException If the user does not provide enough information for the event task.
     */
    private static void parseEventCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        // Check if the user provided enough information for the event task
        if (parts.length < 2) {
            throw new JoeyException("Hey girlie, Please provide both description, start time, and end time for the event task.");
        }

        // Split the user's input to extract event details
        String[] eventParts = parts[1].split("/from", 2);

        // Check if the event description and start time are provided
        if (eventParts.length < 2) {
            throw new JoeyException("Hey girlie, Please provide both description, start time, and end time for the event task.");
        }

        // Extract event description
        String description = eventParts[0].trim();

        // Split the remaining input to get start and end times
        String[] timingParts = eventParts[1].split("/to", 2);

        // Check if both start and end times are provided
        if (timingParts.length < 2) {
            throw new JoeyException("Hey girlie, Please provide both description, start time, and end time for the event task.");
        }

        // Extract start and end times
        String from = timingParts[0].trim();
        String to = timingParts[1].trim();

        // Add the event task to the taskList
        taskList.addTask(new Event(description, from, to, false));

        // Display a confirmation message using the Ui class
        ui.showTaskAdded(taskList.getTasks().get(taskList.getTasks().size() - 1), taskList.getTasks().size());
    }

    /**
     * Parses the "mark" command and marks a task as done in the taskList.
     *
     * @param parts    The parts of the user command.
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui       The Ui object for displaying messages.
     * @throws JoeyException If the user does not provide a valid task number to mark as done.
     */
    private static void parseMarkCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        // Check if the user provided a valid task number to mark as done
        if (parts.length < 2) {
            throw new JoeyException("Hey girlie, Please provide a valid task number to mark as done.");
        }

        try {
            // Parse the task number provided by the user
            int taskIndex = Integer.parseInt(parts[1]) - 1;

            // Mark the task at the specified index as done in the taskList
            taskList.markTaskDone(taskIndex);

            // Display the updated task list using the Ui class
            ui.showTaskList(taskList.getTasks());
        } catch (NumberFormatException e) {
            // Handle the case where the user entered a non-numeric task number
            throw new JoeyException("Hey girlie, Please enter a valid task number to mark as done.");
        }
    }


    /**
     * Parses the "unmark" command and marks a task as not done in the taskList.
     *
     * @param parts    The parts of the user command.
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui       The Ui object for displaying messages.
     * @throws JoeyException If the user does not provide a valid task number to mark as not done.
     */
    private static void parseUnmarkCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        // Check if the user provided a valid task number to mark as not done
        if (parts.length < 2) {
            throw new JoeyException("Hey girlie, Please provide a valid task number to mark as not done.");
        }

        try {
            // Parse the task number provided by the user
            int taskIndex = Integer.parseInt(parts[1]) - 1;

            // Mark the task at the specified index as not done in the taskList
            taskList.markTaskNotDone(taskIndex);

            // Display the updated task list using the Ui class
            ui.showTaskList(taskList.getTasks());
        } catch (NumberFormatException e) {
            // Handle the case where the user entered a non-numeric task number
            throw new JoeyException("Hey girlie, Please enter a valid task number to mark as not done.");
        }
    }


    /**
     * Parses the "delete" command and removes a task from the taskList.
     *
     * @param parts    The parts of the user command.
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui       The Ui object for displaying messages.
     * @throws JoeyException If the user does not provide a valid task number to delete.
     */
    private static void parseDeleteCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        // Check if the user provided a valid task number to delete
        if (parts.length < 2) {
            throw new JoeyException("Hey girlie, Please provide a valid task number to delete.");
        }

        try {
            // Parse the task number provided by the user
            int taskIndex = Integer.parseInt(parts[1]) - 1;

            // Remove the task at the specified index from the taskList
            taskList.removeTask(taskIndex);

            // Display the updated task list using the Ui class
            ui.showTaskList(taskList.getTasks());
        } catch (NumberFormatException e) {
            // Handle the case where the user entered a non-numeric task number
            throw new JoeyException("Hey girlie, Please enter a valid task number to delete.");
        }
    }

    /**
     * Parses the "find" command and searches for tasks containing a specified keyword.
     *
     * @param parts    The parts of the user command.
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui       The Ui object for displaying messages.
     * @throws JoeyException If the user does not provide a keyword to search for.
     */
    private static void parseFindCommand(String[] parts, TaskList taskList, Ui ui) throws JoeyException {
        // Check if the user provided a keyword to search for
        if (parts.length < 2) {
            throw new JoeyException("Hey girlie, Please provide a keyword to search for.");
        }

        // Extract the keyword from the user's command
        String keyword = parts[1].toLowerCase();
        // Create a list to store matching tasks
        ArrayList<Task> matchingTasks = new ArrayList<>();

        // Iterate through the existing tasks in the taskList
        for (Task task : taskList.getTasks()) {
            // Check if the task's description contains the provided keyword (case-insensitive)
            if (task.getDescription().toLowerCase().contains(keyword)) {
                // Add the matching task to the list
                matchingTasks.add(task);
            }
        }

        // Display the matching tasks using the Ui class
        ui.showMatchingTasks(matchingTasks);
    }
}
