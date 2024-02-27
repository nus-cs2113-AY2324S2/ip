package joe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline subclass that inherits from Task
 */
public class Deadline extends Task {
    protected static final String DEADLINE_SYMBOL = "[D]";
    protected LocalDateTime finishBy;
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy hh:mma");

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    public Deadline(String taskName, LocalDateTime finishBy) {
        super(taskName);
        this.finishBy = finishBy;
    }

    @Override
    public String getTaskStatus() {
        return DEADLINE_SYMBOL + super.getTaskStatus() + " (by: " + finishBy.format(FORMATTER) + ")";
    }
}
