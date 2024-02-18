package natsu.command;

import natsu.exception.InvalidCommandException;
import natsu.task.Deadline;
import natsu.util.Printer;
import static natsu.util.TaskManager.list;

public class AddDeadlineCommand {

    public AddDeadlineCommand(String userInput) throws InvalidCommandException {
        if (!userInput.contains(CommandConstants.DEADLINE_INDICATOR)) {
            throw new InvalidCommandException("     I'm terribly sorry, but do add '/by' along with the deadline of the task. Please try again!");
        } else if (userInput.indexOf(CommandConstants.DEADLINE_INDICATOR) == CommandConstants.DEADLINE_COMMAND_PREFIX_LENGTH) {
            throw new InvalidCommandException("     I'm terribly sorry, but the description of a deadline cannot be empty. Please try again!");
        } else if (userInput.indexOf(CommandConstants.DEADLINE_INDICATOR) + CommandConstants.DEADLINE_INDICATOR.length() >= userInput.length()) {
            throw new InvalidCommandException("     I'm terribly sorry, but the deadline date/time cannot be empty. Please try again!");
        } else {
            int byIndex = userInput.indexOf(CommandConstants.DEADLINE_INDICATOR);
            String deadlineDescription = userInput.substring(CommandConstants.DEADLINE_COMMAND_PREFIX_LENGTH, byIndex).trim();
            String deadlineBy = userInput.substring(byIndex + CommandConstants.DEADLINE_INDICATOR.length() + 1).trim();
            Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
            list.add(deadline);
            Printer.printTaskAdded(deadline.toString());
        }
    }
}
