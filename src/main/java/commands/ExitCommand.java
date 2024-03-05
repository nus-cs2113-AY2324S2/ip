package commands;

import exceptions.KikuException;
import java.io.IOException;
import taskList.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * A command to exit and terminate KikuBot.
 * This command is responsible for triggering the save operation for current tasks into storage
 * and then signaling the application to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the application.
     * It saves the current state of tasks into storage and displays an exit message to the user.
     * If an error occurs during the saving process, an error message is displayed.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     * @throws KikuException If an error occurs during the task saving process.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KikuException {
        try {
            storage.saveTasks(tasks.getTasks());
            ui.exitMessage();
        } catch (IOException e) {
            ui.errorMessage("OOPS! An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
