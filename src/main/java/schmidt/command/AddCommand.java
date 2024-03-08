package schmidt.command;

import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.Task;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * Represents am add command to add a Todo, Deadline, or Event task to the task list.
 */
public class AddCommand extends Command {
    private final Task taskToAdd;

    /**
     * Constructs an add command with the task to add.
     *
     * @param taskToAdd the task to add
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Adds a task to the task list and saves it to storage.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     * @throws SchmidtException if there was an error adding the task or saving it to storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SchmidtException {
        try {
            tasks.addTask(taskToAdd);
        } catch (Exception e) {
            throw new SchmidtException("Sorry, there was an error adding the task.");
        }

        try {
            storage.save(tasks);
        } catch (Exception e) {
            throw new SchmidtException("Sorry, there was an error saving the task to storage.");
        }

        ui.printTaskAdded(taskToAdd, tasks.getSize());
    }
}

