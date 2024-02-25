package bob.command;

import bob.utils.TaskManager;

public class DeadlineCommand extends Command {
    private final String taskName;
    private final String dueDate;

    public DeadlineCommand(TaskManager taskManager, String taskName, String dueDate) {
        super(taskManager);
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    @Override
    public String executeCommand() {
        return taskManager.addDeadline(this.taskName, this.dueDate);
    }
}
