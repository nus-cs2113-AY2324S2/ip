package schmidt.command;

import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.Task;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * Represents a mark command to mark a task as done or undone in the task list.
 */
public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean isDone;

    /**
     * Constructs a mark command with the index of the task to mark and whether to mark it as done or undone.
     *
     * @param taskIndex the index of the task to mark
     * @param isDone whether to mark the task as done or undone
     */
    public MarkCommand(int taskIndex, boolean isDone) {
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    /**
     * Marks a task as done or undone and saves it to storage.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     * @throws SchmidtException if there was an error marking the task or saving it to storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SchmidtException {
        Task markedTask;
        try {
            markedTask = tasks.setDone(taskIndex, isDone);
        } catch (Exception e) {
            throw new SchmidtException("Sorry, I cannot find a task with the number " + (taskIndex + 1));
        }

        try {
            storage.save(tasks);
        } catch (Exception e) {
            throw new SchmidtException("Sorry, there was an error saving the task to storage.");
        }

        if (isDone) {
            ui.printTaskDone(markedTask);
        } else {
            ui.printTaskUndone(markedTask);
        }
    }
}
