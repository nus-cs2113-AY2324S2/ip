package nick.command;

import nick.NickException;
import nick.task.TaskList;
import nick.ui.Ui;
import nick.storage.Storage;

/**
 * An abstract Command class at the top of the type hierarchy for the rest of the commands.
 * Includes the execute and isExit method to be implemented by the other commands.
 */
public abstract class Command {
    private TaskList tasks;

    /**
     * Executes the command.
     *
     * @param tasks ArrayList of Task objects.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NickException;

    /**
     * Returns the boolean to exiting the program.
     *
     * @return Boolean of false which continues program.
     */
    public boolean isExit() {
        return false;
    }
}
