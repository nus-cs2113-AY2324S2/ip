/**
 * The Deadline class represents a task with a deadline.
 * It is a subclass of the Task class.
 */
public class Deadline extends Task {

    /** The deadline for the task. */
    protected String by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]"  +"[" + this.getStatusIcon()+ "] " + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline object for saving to a file.
     *
     * @return A string representation of the Deadline object for saving to a file.
     */
    @Override
    public String toFileString() {
        return "D|" + super.toFileString() + "|" + by; // Prefix with "D" to indicate Deadline
    }
}
