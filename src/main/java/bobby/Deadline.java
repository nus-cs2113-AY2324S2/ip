package bobby;

/**
 * Represents a deadline task, which is a type of task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline instance with the given description, completion status, and due date.
     *
     * @param description The description of the deadline task.
     * @param isDone Indicates whether the deadline task is done or not.
     * @param by The due date of the deadline task.
     */
    public Deadline(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Gets the due date of the deadline task.
     *
     * @return The due date of the deadline task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task, including its status icon, description, and due date,
     * prefixed with "[D]".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
