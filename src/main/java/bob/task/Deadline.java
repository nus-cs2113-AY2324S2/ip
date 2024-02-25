package bob.task;

/**
 * Deadline Task class. Used to represent Tasks with a due date.
 */
public class Deadline extends Task {
    private final String dueDate;

    private Deadline(String taskName, boolean isCompleted, String dueDate) {
        super(taskName, isCompleted);
        this.dueDate = dueDate;
    }

    public Deadline(String taskName, String dueDate) {
        super(taskName, false);
        this.dueDate = dueDate;
    }

    /**
     * Due date getter method.
     *
     * @return String representation of the Task due date.
     */
    public String getDueDate() {
        return this.dueDate;
    }

    public Task markTaskAsComplete() {
        return new Deadline(this.taskName, true, this.dueDate);
    }

    public Task markTaskAsIncomplete() {
        return new Deadline(this.taskName, false, this.dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.dueDate);
    }
}
