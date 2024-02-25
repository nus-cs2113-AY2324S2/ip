package bob.command;

import bob.utils.TaskManager;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String taskName;
    private final LocalDateTime dueDate;

    public DeadlineCommand(TaskManager taskManager, String taskName, LocalDateTime dueDate) {
        super(taskManager);
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    @Override
    public String executeCommand() {
        return taskManager.addDeadline(this.taskName, this.dueDate);
    }
}
