package schmidt.command;

import schmidt.storage.Storage;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND = "bye";

    /**
     * Exits the program.
     *
     * @param tasks the list of tasks.
     * @param ui the user interface.
     * @param storage the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

    /**
     * Returns true since the command is an exit command.
     *
     * @return true since the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
