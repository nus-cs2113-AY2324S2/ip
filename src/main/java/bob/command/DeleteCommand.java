package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.utils.TaskManager;

/**
 * Class representing a "delete" command.
 */
public class DeleteCommand extends Command {
    private final int taskId;

    /**
     * Creates a DeleteCommand Object.
     * @param taskManager Current TaskManager instance.
     * @param taskId ID of Task to be deleted.
     */
    public DeleteCommand(TaskManager taskManager, int taskId) {
        super(taskManager);
        this.taskId = taskId;
    }

    @Override
    public String executeCommand() throws InvalidTaskNumberException {
        try {
            return taskManager.deleteTask(this.taskId);
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            throw new InvalidTaskNumberException("DELETE");
        }
    }
}
