package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.utils.TaskManager;

/**
 * Class representing an "unmark" command.
 */
public class UnmarkCommand extends Command {
    private final int taskId;

    /**
     * Creates an UnmarkCommand Object.
     * @param taskManager Current TaskManager instance.
     * @param taskId ID of Task to be marked as undone/incomplete.
     */
    public UnmarkCommand(TaskManager taskManager, int taskId) {
        super(taskManager);
        this.taskId = taskId;
    }

    @Override
    public String executeCommand() throws InvalidTaskNumberException {
        try {
            return taskManager.updateTaskProgress(this.taskId, "UNMARK");
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            throw new InvalidTaskNumberException("UNMARK");
        }
    }
}
