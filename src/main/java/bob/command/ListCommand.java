package bob.command;

import bob.utils.TaskManager;

public class ListCommand extends Command {
    public ListCommand(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public String executeCommand() {
        return taskManager.listTasks();
    }
}
