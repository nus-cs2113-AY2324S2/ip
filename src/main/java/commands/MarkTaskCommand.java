package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * A command to mark a specific task in the task list as done.
 * Based on the given index, this command changes the isDone status of a task to true
 * to indicate completion.
 */
public class MarkTaskCommand extends Command {
    private final int taskIndex;

    public MarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by marking the specified task as done, if the index is valid.
     * It then displays a message indicating that the task has been marked as done.
     * If the index is invalid (out of bounds), an error message is shown to the user.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex >= 0 && taskIndex < tasks.getTasks().size()) {
            tasks.markTaskAsDone(taskIndex);
            ui.showTask("Awesomeee! I've marked this task as done: \n"
                    + tasks.getTasks().get(taskIndex));
        } else {
            ui.errorMessage("OPPS! Task index out of bounds.");
        }
    }
}
