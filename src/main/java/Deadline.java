/**
 * The Deadline class represents a task with a deadline.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    protected String by;

    /**
     * Constructs a Deadline object with the specified description, deadline, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline of the task.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D] " + super.getStatusIcon() + " " + super.toString() + " (by: " + by + ")";
    }
}
