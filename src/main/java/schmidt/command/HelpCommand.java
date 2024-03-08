package schmidt.command;

import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * Represents a command to display the help message.
 */
public class HelpCommand extends Command {

    /**
     * Displays the help message.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     * @throws SchmidtException if there was an error displaying the help message
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SchmidtException {
        ui.printHelpMessage();
    }
}
