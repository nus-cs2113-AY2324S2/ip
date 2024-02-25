package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.utils.TaskManager;

public class UnmarkCommand extends Command {
    private final int taskId;

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
