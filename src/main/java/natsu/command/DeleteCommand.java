package natsu.command;

import natsu.exception.InvalidCommandException;
import natsu.task.Task;

import static natsu.util.Printer.printTaskDeleted;
import static natsu.util.TaskManager.list;

public class DeleteCommand {
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
