/*
 * Deadline class represents a task with a deadline
 * A <code>Deadline</code> object represents a task with a deadline
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline object with the given description and deadline.
     *
     * @param description The description of the deadline
     * @param by The deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    /**
     * Returns the task type of the deadline
     * Overrides the toString method in Task
     * Indicates the deadline of the task
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

