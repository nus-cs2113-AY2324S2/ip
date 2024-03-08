package schmidt.command;

import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * Represents the parent class for a command to be executed by Schmidt.
 */
public abstract class Command {
    public static final int TYPE_INDEX = 0;
    protected static final String ERROR_SAVING_TO_STORAGE_MESSAGE = "Sorry, there was an error saving the task to storage.";

    /**
     * Executes the command.
     *
     * @param tasks the list of tasks.
     * @param ui the user interface.
     * @param storage the storage.
     * @throws SchmidtException if there was an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SchmidtException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return true if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}

