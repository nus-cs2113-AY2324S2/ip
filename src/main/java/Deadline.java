import java.time.LocalDateTime;

/**
 * Deadline task class.
 *
 * @param end The due date of the task.
 * @param taskType Specify what type of task it is.
 */
public class Deadline extends Task{
    protected LocalDateTime end;
    protected String taskType;

    public Deadline(String task, LocalDateTime end, String taskType) {
        super(task);
        this.end = end;
        this.taskType = taskType;
    }

    /** Returns the task type */
    @Override
    public String getTaskType() {
        return taskType;
    }

    /** Returns the due date for the task */
    @Override
    public LocalDateTime getEnd() {
        return end;
    }
}
