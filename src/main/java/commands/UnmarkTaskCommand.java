package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * A command to mark a specific task in the task list as not done.
 * Based on the given index, this command changes the isDone status of a task to false
 * to indicate in progress of completion.
 */
public class UnmarkTaskCommand extends Command {
    private final int taskIndex;

    public UnmarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by marking the specified task as not done, if the index is valid.
     * It then displays a message indicating that the task has been marked as not done (unmarked).
     * If the index is invalid (out of bounds), an error message is shown to the user.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex >= 0 && taskIndex < tasks.getTasks().size()) {
            tasks.markTaskAsNotDone(taskIndex);
            ui.showTask("OK! I've marked this task as not done yet: \n"
                    + tasks.getTasks().get(taskIndex));
        } else {
            ui.errorMessage("OOPS! Task index out of bounds :<");
        }
    }
}
