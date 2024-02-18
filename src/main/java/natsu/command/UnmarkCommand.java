package natsu.command;

import natsu.exception.InvalidCommandException;

import static natsu.util.Printer.printTaskUndone;
import static natsu.util.TaskManager.list;

public class UnmarkCommand {
    public UnmarkCommand(String userInput) throws InvalidCommandException{
        int itemIndex = Integer.parseInt(userInput.substring(CommandConstants.UNMARK_COMMAND_LENGTH)) - 1;
        if (itemIndex >= list.size() || itemIndex < 0) {
            throw new InvalidCommandException("     You have " + list.size() + " items in your list, please enter a valid number.");
        } else {
            list.get(itemIndex).markAsUndone();
            printTaskUndone(list.get(itemIndex).toString());
        }
    }
}
