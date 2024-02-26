package joe.command;

import joe.task.TaskManager;

public interface Command {
    void executeCommand(TaskManager taskManager);
    boolean isExit();
}
