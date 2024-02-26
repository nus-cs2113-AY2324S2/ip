package natsu.command;

import natsu.exception.InvalidCommandException;
import natsu.task.Deadline;
import natsu.util.Ui;

import static natsu.util.TaskList.list;

/**
 * Represents a command to add a new deadline task to the task list.
 * The command parses user input to extract the task description and its deadline,
 * then creates a new {@link Deadline} object and adds it to the task list.
 */
public class AddDeadlineCommand {

    /**
     * Constructs an {@code AddDeadlineCommand} and adds a new deadline to the task list.
     *
     * @param userInput The full user input string containing the deadline description and date/time.
     * @throws InvalidCommandException If the input does not contain a '/by' indicator,
     *                                 if the description of the deadline is empty,
     *                                 or if the deadline date/time is not specified.
     */
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
            Ui.printTaskAdded(deadline.toString());
        }
    }
}
