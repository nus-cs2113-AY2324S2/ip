package helpy.command;

import helpy.Storage;
import helpy.Ui;
import helpy.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command{
    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param taskList The TaskList containing the tasks to be listed
     * @param ui The user interface for displaying output and messages.
     * @param storage The storage system for managing task data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
