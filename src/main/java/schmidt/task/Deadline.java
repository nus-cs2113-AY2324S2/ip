package schmidt.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a deadline task with the specified description and deadline.
     *
     * @param description the description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return the deadline of the task
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
