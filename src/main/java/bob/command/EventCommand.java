package bob.command;

import bob.utils.TaskManager;

import java.time.LocalDateTime;

/**
 * Class representing an "event" command.
 */
public class EventCommand extends Command {
    private final String taskName;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Creates an EventCommand Object.
     *
     * @param taskManager Current TaskManager Instance
     * @param taskName    Name of Event Task
     * @param startDate   Start Date of Event Task
     * @param endDate     End Date of Event Task
     */
    public EventCommand(TaskManager taskManager, String taskName, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskManager);
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String executeCommand() {
        return taskManager.addEvent(taskName, startDate, endDate);
    }
}
