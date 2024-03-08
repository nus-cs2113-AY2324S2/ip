package schmidt.command;

import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.Task;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * Represents a delete command to remove a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a delete command with the index of the task to delete.
     *
     * @param index the index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task from the task list and saves it to storage.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     * @throws SchmidtException if there was an error deleting the task or saving it to storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SchmidtException {
        Task deletedTask;
        try {
            deletedTask = tasks.deleteTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new SchmidtException("Sorry, I cannot find a task with the number " + (index + 1));
        }

        try {
            storage.save(tasks);
        } catch (Exception e) {
            throw new SchmidtException("Sorry, there was an error saving the task to storage.");
        }

        ui.printTaskDeleted(deletedTask, tasks.getSize());
    }
}
