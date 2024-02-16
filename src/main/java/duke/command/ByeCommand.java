package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the Duke chatbot.
 */
public class ByeCommand implements Command {

    /**
     * Executes the exit command, displaying the exit message and exiting the Duke chatbot.
     *
     * @param taskList The lists of tasks of the Duke chatbot.
     * @param ui The user interface of the Duke chatbot.
     * @param storage The file storage of the Duke chatbot.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
    }

    /**
     * Indicates whether this is an exit command.
     * Returns true since this is an exit command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
