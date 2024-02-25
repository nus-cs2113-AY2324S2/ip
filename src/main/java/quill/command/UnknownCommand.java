package quill.command;

import quill.task.TaskList;
import quill.ui.TextUi;

/**
 * The UnknownCommand Class represents a command that is unknown.
 * It extends the Command class and includes specific behavior for unknown commands.
 */
public class UnknownCommand extends Command{

    /**
     * Constructs the UnknownCommand object with the specified commandWord and parameter.
     *
     * @param commandWord The command word.
     * @param parameter The user input excluding the command word.
     */
    public UnknownCommand(String commandWord, String parameter) {
        super(commandWord, parameter);
    }

    /**
     * Executes the unknownCommand to display an error message.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui) {
        TextUi.showUnknownMessage();
    }
}
