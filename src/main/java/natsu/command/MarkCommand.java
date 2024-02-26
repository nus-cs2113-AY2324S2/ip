package natsu.command;

import natsu.exception.InvalidCommandException;

import static natsu.util.Ui.printTaskDone;
import static natsu.util.TaskList.list;

/**
 * Represents a command to mark a task as done in the task list.
 * This command updates the status of a specified task to indicate
 * that it has been completed, based on the user's input.
 */
public class MarkCommand {

    /**
     * Constructs a {@code MarkCommand} instance that marks a task as done.
     * It parses the user input to identify which task to mark, validates the
     * input to ensure it corresponds to an existing task, updates the task's
     * status to done, and then displays a confirmation message.
     *
     * @param userInput The full user input string, including the command and the task number to mark as done.
     * @throws InvalidCommandException If the specified task number is invalid or out of bounds.
     */
    public MarkCommand(String userInput) throws InvalidCommandException {
        int itemIndex = Integer.parseInt(userInput.substring(CommandConstants.MARK_COMMAND_LENGTH)) - 1;
        if (itemIndex >= list.size() || itemIndex < 0) {
            throw new InvalidCommandException("     You have " + list.size() + " items in your list, please enter a valid number.");
        } else {
            list.get(itemIndex).markAsDone();
            printTaskDone(list.get(itemIndex).toString());
        }
    }
}
