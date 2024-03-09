/**
 * This class represents a deadline task with a due date.
 * It inherits from the `Task` class and adds a due date field.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description the description of the task
     * @param by the due date for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the `toString` method from the `Task` class to provide a specific
     * string representation for deadline tasks, including the due date.
     *
     * @return the string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getDescription() + " (by: " + by + ")";
    }

    /**
     * Implements the `toFileString` method from the `Task` class to provide a string
     * representation suitable for storing the deadline task in a file.
     *
     * @return the string representation of the deadline task for file storage
     */
    @Override
    public String toFileString() {
        return "[D]" + super.getStatusIcon() + " " + super.getDescription() + " (by: " + by + ")";
    }
}
