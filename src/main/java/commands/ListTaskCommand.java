package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * A command to list all tasks in the task list.
 * This command displays all tasks currently managed by Kikubot to the user.
 */
public class ListTaskCommand extends Command {
    /**
     * Executes the command by displaying all tasks in the task list to the user.
     * If the task list is empty, a message indicating so is displayed.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showTask("OOPS! The list is currently empty :<");
        } else {
            ui.listTasks(tasks);
        }
    }
}
