package natsu.command;

import natsu.exception.InvalidCommandException;

import static natsu.util.Printer.printTaskUndone;
import static natsu.util.TaskManager.list;
import static natsu.util.TaskManager.taskCount;

public class UnmarkCommand {
    public UnmarkCommand(String userInput) throws InvalidCommandException{
        int itemIndex = Integer.parseInt(userInput.substring(CommandConstants.UNMARK_COMMAND_LENGTH)) - 1;
        if (itemIndex >= taskCount || itemIndex < 0) {
            throw new InvalidCommandException("     You have " + taskCount + " items in your list, please enter a valid number.");
        } else {
            list[itemIndex].markAsUndone();
            printTaskUndone(list[itemIndex].toString());
        }
    }
}
