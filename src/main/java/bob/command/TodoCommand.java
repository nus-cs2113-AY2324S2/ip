package bob.command;

import bob.utils.TaskManager;

public class TodoCommand extends Command {
    private final String taskName;

    public TodoCommand(TaskManager taskManager, String taskName) {
        super(taskManager);
        this.taskName = taskName;
    }

    @Override
    public String executeCommand() {
        return taskManager.addTodo(this.taskName);
    }
}
