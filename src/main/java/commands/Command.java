package commands;

import exceptions.KikuException;
import taskList.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Abstract base class for all command types in KikuBot
 * Command encapsulate all the information for execution based on command type,
 * including the necessary parameters and their interaction with TaskList, Ui, and Storage
 */
public abstract class Command {
    /**
     * Executes the command with the given TaskList, Ui, and Storage components.
     * This method defines the core behaviour of the command
     * and is implemented by all concrete command classes.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     * @throws KikuException if an error occurs during command execution, including issues with task manipulation or IO operations.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KikuException;
}
