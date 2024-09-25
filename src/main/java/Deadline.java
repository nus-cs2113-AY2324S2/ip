/**
 * Deadline class represents a task with a specific deadline, extending the Task class.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a formatted string representation of the Deadline task.
     *
     * @return Formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a formatted string representation of the Deadline task for saving to a file.
     *
     * @return Formatted string representing the Deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return "D|" + super.toFileString() + "|" + by;
    }
}
