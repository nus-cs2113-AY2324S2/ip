package natsu.command;

import natsu.exception.InvalidCommandException;

import static natsu.util.Ui.printTaskDone;
import static natsu.util.TaskList.list;

public class MarkCommand {
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
