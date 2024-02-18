package natsu.command;

import natsu.exception.InvalidCommandException;

import static natsu.util.Printer.printTaskDeleted;
import static natsu.util.TaskManager.list;
import static natsu.util.TaskManager.taskCount;

public class DeleteCommand {
    public DeleteCommand(String userInput) throws InvalidCommandException {
        if (userInput.length() <= CommandConstants.DELETE_COMMAND_LENGTH) {
            throw new InvalidCommandException("     I'm terribly sorry, please enter a valid number and try again!");
        }
        int taskNumber = Integer.parseInt(userInput.substring(CommandConstants.DELETE_COMMAND_LENGTH).trim()) - 1;
        if (taskNumber >= taskCount || taskNumber < 0) {
            throw new InvalidCommandException("     You have " + taskCount + " items in your list, please enter a valid number.");
        }
        taskCount--;
        printTaskDeleted(list[taskNumber].toString(), taskCount);
        for (int i = taskNumber; i < taskCount; i++) {
            list[i] = list[i + 1];
        }
        list[taskCount] = null;
    }
}
