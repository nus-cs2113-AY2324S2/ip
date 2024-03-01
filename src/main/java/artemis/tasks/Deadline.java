package artemis.tasks;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Creates a Deadline task with the given task name and due date.
     *
     * @param taskName The name of the task.
     * @param dueDate The due date of the task.
     */
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    /**
     * Returns a formatted string representation of the Deadline
     *
     * @return Formatted string of Deadline
     */
    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", super.getStatusIcon(), super.taskName, dueDate);
    }

    /**
     * Gets the due date of the Deadline task.
     *
     * @return The due date of the Deadline task.
     */
    public String getDueDate() {
        return dueDate;
    }
}
