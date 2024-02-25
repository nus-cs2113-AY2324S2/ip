package bob.command;

import bob.utils.TaskManager;

public class EventCommand extends Command {
    private final String taskName;
    private final String startDate;
    private final String endDate;

    public EventCommand(TaskManager taskManager, String taskName, String startDate, String endDate) {
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
