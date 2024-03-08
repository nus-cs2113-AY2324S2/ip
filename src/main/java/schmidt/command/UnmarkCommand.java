package schmidt.command;

import schmidt.exception.SchmidtException;
import schmidt.storage.Storage;
import schmidt.task.Task;
import schmidt.task.TaskList;
import schmidt.ui.Ui;

/**
 * Represents a mark command to mark a task as done or undone in the task list.
 */
public class UnmarkCommand extends Command {
    private static final String INVALID_TASK_NUMBER_MESSAGE = "Sorry, I cannot find a task with the number ";
    private static final String ERROR_SAVING_TO_STORAGE_MESSAGE = "Sorry, there was an error saving the task to storage.";
    public static final String COMMAND = "unmark";
    public static final String INCORRECT_FORMAT_MESSAGE = "Please follow the unmark command format\n\tunmark <task number>";
    private final int taskIndex;

    /**
     * Constructs a mark command with the index of the task to mark and whether to mark it as done or undone.
     *
     * @param taskIndex the index of the task to mark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks a task as done or undone and saves it to storage.
     *
     * @param tasks the list of tasks.
     * @param ui the user interface.
     * @param storage the storage.
     * @throws SchmidtException if there was an error marking the task or saving it to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SchmidtException {
        Task markedTask;
        try {
            markedTask = tasks.setDone(taskIndex, false);
        } catch (Exception e) {
            throw new SchmidtException(INVALID_TASK_NUMBER_MESSAGE + (taskIndex + 1));
        }

        try {
            storage.save(tasks);
        } catch (Exception e) {
            throw new SchmidtException(ERROR_SAVING_TO_STORAGE_MESSAGE);
        }

        ui.printTaskUndone(markedTask);
    }
}
