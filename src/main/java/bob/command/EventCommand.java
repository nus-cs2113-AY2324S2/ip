package bob.command;

import bob.utils.TaskManager;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String taskName;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public EventCommand(TaskManager taskManager, String taskName, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskManager);
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String executeCommand() {
        return taskManager.addEvent(this.taskName, this.startDate, this.endDate);
    }
}
