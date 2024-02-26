package natsu.command;

import natsu.exception.InvalidCommandException;
import natsu.task.Task;

import static natsu.util.Ui.printTaskDeleted;
import static natsu.util.TaskList.list;

/**
 * Represents a command to delete a task from the task list.
 * This command processes user input to identify and remove the specified task,
 * then communicates the change to the user.
 */
public class DeleteCommand {

    /**
     * Constructs a {@code DeleteCommand} instance that processes the deletion of a task.
     * Validates the user input to ensure it specifies a valid task number and
     * removes the specified task from the task list if it exists.
     *
     * @param userInput The full user input string, including the command and the task number to delete.
     * @throws InvalidCommandException If the specified task number is invalid, out of bounds,
     *                                 or if the input format does not meet expectations.
     */
    public DeleteCommand(String userInput) throws InvalidCommandException {
        if (userInput.length() <= CommandConstants.DELETE_COMMAND_LENGTH) {
            throw new InvalidCommandException("     I'm terribly sorry, please enter a valid number and try again!");
        }
        int taskNumber = Integer.parseInt(userInput.substring(CommandConstants.DELETE_COMMAND_LENGTH).trim()) - 1;
        if (taskNumber >= list.size() || taskNumber < 0) {
            throw new InvalidCommandException("     You have " + list.size() + " items in your list, please enter a valid number.");
        }
        Task deletedTask = list.get(taskNumber);
        list.remove(taskNumber);
        printTaskDeleted(deletedTask.toString(), list.size());
    }
}
