/**
 * Represents a deadline task with a specific due date.
 * A {@code DeadLine} object encapsulates the details of a task that needs to be done before a specific date.
 */
public class DeadLine extends Task {
    protected String by; // The due date of the deadline task.

    /**
     * Constructs a {@code DeadLine} with the specified task description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the task.
     */
    public DeadLine(String description, String by) {
        super(description); // Call the superclass constructor to set the task description.
        this.by = by; // Set the due date of the deadline task.
    }

    /**
     * Returns the string representation of the deadline task in a format suitable for file storage.
     * The format is "D | isDone | description | by", where "D" indicates a deadline task.
     *
     * @return A string representation of the deadline task for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }

    /**
     * Returns the string representation of the deadline task, including its status (done or not done),
     * description, and due date.
     *
     * @return A string representation of the deadline task, including status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
