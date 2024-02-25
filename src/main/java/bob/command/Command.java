package bob.command;

import bob.exceptions.InvalidTaskNumberException;
import bob.utils.TaskManager;

abstract public class Command {
    protected final TaskManager taskManager;

    public Command(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
    abstract public String executeCommand() throws InvalidTaskNumberException;

    public boolean isExitCommand() {
        return false;
    }
}
