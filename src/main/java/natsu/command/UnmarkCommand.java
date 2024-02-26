package natsu.command;

import natsu.exception.InvalidCommandException;

import static natsu.util.Ui.printTaskUndone;
import static natsu.util.TaskList.list;

/**
 * Represents a command to mark a task as not done in the task list.
 * This command changes the status of a specified task to indicate
 * that it has not been completed, as per the user's input.
 */
public class UnmarkCommand {

    /**
     * Constructs an {@code UnmarkCommand} instance that marks a task as not done.
     * It parses the user input to determine which task to unmark, validates the
     * input to ensure it corresponds to an existing task in the list, updates the task's
     * status to not done, and then displays a confirmation message to the user.
     *
     * @param userInput The full user input string, including the command and the task number to mark as not done.
     * @throws InvalidCommandException If the specified task number is invalid, out of bounds,
     *                                 or if the user input format does not meet the expected criteria.
     */
    public UnmarkCommand(String userInput) throws InvalidCommandException {
        int itemIndex = Integer.parseInt(userInput.substring(CommandConstants.UNMARK_COMMAND_LENGTH)) - 1;
        if (itemIndex >= list.size() || itemIndex < 0) {
            throw new InvalidCommandException("     You have " + list.size() + " items in your list, please enter a valid number.");
        } else {
            list.get(itemIndex).markAsUndone();
            printTaskUndone(list.get(itemIndex).toString());
        }
    }
}
