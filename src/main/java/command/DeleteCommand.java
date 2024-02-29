package carrot.command;

import carrot.parser.Parser;
import carrot.task.TaskList;
import carrot.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * Extends the {@link Command} abstract class.
 */
public class DeleteCommand extends Command {

    /**
     * Executes the delete command, removing a task from the task list.
     * <p>
     * If an invalid task index is provided or an error occurs during deletion,
     * an error message is printed.
     *
     * @param userInput the user input string containing the delete command and task index
     */
    @Override
    public void execute(String userInput) {
        String taskIndex =
                Parser.getCommandArguments(carrot.command.CommandType.DELETE, userInput)[0];
        int taskIndexInt =
                Integer.parseInt(taskIndex) - 1;

        try {
            TaskList.deleteTask(taskIndexInt);
        } catch (Exception e) {
            Ui.printInvalidTaskIndexError();
        }
    }
}
