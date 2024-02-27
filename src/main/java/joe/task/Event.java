package joe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event subclass that inherits from Task
 */
public class Event extends Task {
    protected static final String EVENT_SYMBOL = "[E]";
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");


    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    public Event(String taskName, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskStatus() {
        return EVENT_SYMBOL + super.getTaskStatus()
                + " (from: " + startDate.format(FORMATTER) + " to: " + endDate.format(FORMATTER) + ")";
    }
}
