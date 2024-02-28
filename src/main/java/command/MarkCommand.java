package carrot.command;

import carrot.command.Command;
import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.ui.Ui;

/**
 * Represents a command to mark a task as done.
 * Extends the {@link Command} abstract class.
 */
public class MarkCommand extends Command {

    /**
     * Executes the mark command, marking the specified task as done.
     * <p>
     * If an invalid task index is provided or an error occurs during status change,
     * an error message is printed.
     *
     * @param userInput the user input string containing the mark command and task index
     */
    @Override
    public void execute(String userInput) {
        String taskIndex =
                Parser.getCommandArguments(CommandType.MARK, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex);

        try {
            TaskList.changeTaskStatus(true, taskIndexInt);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }
}
