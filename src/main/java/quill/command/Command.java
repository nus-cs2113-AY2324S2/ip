package quill.command;

import quill.task.TaskList;
import quill.ui.TextUi;

/**
 * The Command class represents an abstract command in the Luke application.
 * It serves as the base class for more specific command types.
 */
public abstract class Command {
    protected String commandWord;
    protected String parameter;

    /**
     * Constructs a command object with the specified command word and parameters.
     *
     * @param commandWord The command word.
     * @param parameter The user input excluding command word.
     */
    public Command(String commandWord, String parameter) {
        this.commandWord = commandWord;
        this.parameter = parameter;
    }

    /**
     * Executes the Command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface for displaying messages.
     */
    public void execute (TaskList tasks, TextUi ui) {
    }
}
