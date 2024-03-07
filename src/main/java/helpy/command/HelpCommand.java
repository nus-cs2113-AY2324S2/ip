package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.TaskList;

/**
 * Represents a command to display the help menu.
 */
public class HelpCommand extends Command{
    /**
     * Executes the HelpCommand by displaying the help menu to the user.
     *
     * @param taskList The task list on which the command should operate.
     * @param ui       The user interface for displaying output and messages.
     * @param storage  The storage system for managing task data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
