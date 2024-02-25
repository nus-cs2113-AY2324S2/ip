package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.utils.TaskManager;

public class MarkCommand extends Command {
    private final int taskId;

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
