/**
 * Represents a deadline-type task in the task list.
 * A deadline task includes a description and a deadline date or time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline instance.
     * @param description The text description of the deadline task.
     * @param by The deadline date or time for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task, including its status, description and deadline date or time.
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + getDescription() + " (by: " + by + ")";
    }
}
