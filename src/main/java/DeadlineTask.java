/**
 * The DeadlineTask class represents a task with a deadline.
 * It extends the Task class.
 */
public class DeadlineTask extends Task {
    protected String by;

    /**
     * Constructs a DeadlineTask object with the provided description and deadline.
     * @param description The description of the deadline task
     * @param by The deadline of the task
     */
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the description of the deadline task.
     * @return The description of the deadline task
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Gets the deadline of the deadline task.
     * @return The deadline of the deadline task
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     * @return A string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
