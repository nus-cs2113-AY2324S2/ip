package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.utils.TaskManager;

/**
 * Class representing a "mark" command.
 */
public class MarkCommand extends Command {
    private final int taskId;

    /**
     * Creates a MarkCommand Object.
     * @param taskManager Current TaskManager instance.
     * @param taskId ID of Task to be marked as done.
     */
    public MarkCommand(TaskManager taskManager, int taskId) {
        super(taskManager);
        this.taskId = taskId;
    }

    @Override
    public String executeCommand() throws InvalidTaskNumberException {
        try {
            return taskManager.updateTaskProgress(this.taskId, "MARK");
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            throw new InvalidTaskNumberException("MARK");
        }
    }
}
