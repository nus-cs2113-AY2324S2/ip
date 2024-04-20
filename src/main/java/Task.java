import java.time.LocalDateTime;

/**
 * Task class responsible to the task methods and params.
 *
 * @param task Title of the task.
 * @param isDone Whether the task is marked done or not.
 */
public class Task {
    protected String task;
    protected boolean isDone;

    public Task (String task) {
        this.task = task;
        this.isDone = false;
    }

/** Marks or unmarks the task as done */
    public void markDone() {
        this.isDone = !isDone;
    }

    /** Returns the type of task */
    public String getTaskType() {
        return "";
    }

    /** Returns null as no start date */
    public LocalDateTime getStart() {
        return null;
    }

    /** Returns null as no end date */
    public LocalDateTime getEnd() {
        return null;
    }
}
