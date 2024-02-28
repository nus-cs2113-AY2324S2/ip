package carrot.task;

/**
 * Represents a deadline task, a type of task that requires a description and a specific due date.
 * <p>
 * A deadline task inherits properties and behaviors from the {@link Task} class.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a deadline task with the specified description and due date.
     *
     * @param description the description of the deadline task
     * @param by          the due date of the deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a deadline task with the specified description, due date, and completion status.
     *
     * @param description the description of the deadline task
     * @param by          the due date of the deadline task
     * @param isDone      the completion status of the deadline task
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     * <p>
     * The string includes the task type indicator "[D]" followed by the status icon,
     * description, and due date of the deadline task.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the deadline task in a format suitable for storing in a file.
     * <p>
     * The deadline task is formatted with the task type indicator "D" followed by the format of the parent task
     * and the due date.
     * <p>
     * Any double quotes in the due date are escaped to prevent interference with the file format.
     *
     * @return the deadline task in file format
     */
    @Override
    public String toFileFormat() {
        // Escape characters: usertyped " would not interfere with file format
        String escapedBy = by.replace("\"", "\\\"");

        return "D" + super.toFileFormat() + " | \"" + by + "\"";
    }
}
