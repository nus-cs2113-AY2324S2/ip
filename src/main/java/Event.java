import java.time.LocalDateTime;

/**
 * Event task class.
 *
 * @param start The start date of the task.
 * @param end The due date of the task.
 * @param taskType Specify what type of task it is.
 */
public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected String taskType;

    public Event(String task, LocalDateTime start, LocalDateTime end, String taskType) {
        super(task);
        this.start = start;
        this.end = end;
        this.taskType = taskType;
    }

    /** Returns the task type */
    @Override
    public String getTaskType() {
        return taskType;
    }

    /** Returns the task start date */
    @Override
    public LocalDateTime getStart() {
        return start;
    }

    /** Returns the task end date */
    @Override
    public LocalDateTime getEnd() {
        return end;
    }
}
