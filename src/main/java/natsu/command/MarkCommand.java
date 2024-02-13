package natsu.command;

import natsu.exception.InvalidCommandException;

import static natsu.util.Printer.printTaskDone;
import static natsu.util.TaskManager.list;
import static natsu.util.TaskManager.taskCount;

public class MarkCommand {
    public MarkCommand(String userInput) throws InvalidCommandException {
        int itemIndex = Integer.parseInt(userInput.substring(CommandConstants.MARK_COMMAND_LENGTH)) - 1;
        if (itemIndex >= taskCount || itemIndex < 0) {
            throw new InvalidCommandException("     You have " + taskCount + " items in your list, please enter a valid number.");
        } else {
            list[itemIndex].markAsDone();
            printTaskDone(list[itemIndex].toString());
        }
    }
}
