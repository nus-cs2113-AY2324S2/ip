package commands;

import taskList.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Task;

/**
 * A command to delete a task from the task list.
 * This command removes a task based on its index in the task list.
 */
public class DeleteTaskCommand extends Command {
    private final int taskIndex;
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by removing the specified task from the task list.
     * If the index is valid, display a message about the removal and update the task count.
     * If the index is invalid, an error message is shown to the user.
     *
     * @param tasks The TaskList currently managed by KikuBot, which can be modified by the command.
     * @param ui The Ui component of KikuBot used for interacting with the user.
     * @param storage The Storage component of KikuBot, responsible for tracking task list changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskIndex >= 0 && taskIndex < tasks.getTasks().size()) {
            Task removedTask = tasks.getTasks().get(taskIndex);
            tasks.deleteTask(taskIndex);
            ui.showTask("Alright, I've removed this task: \n" + removedTask);
            ui.showTask("Now you have " + tasks.getTasks().size() + " tasks in the list :>");
        } else {
            ui.errorMessage("OOPS! Task index out of bounds :<");
        }
    }
}
