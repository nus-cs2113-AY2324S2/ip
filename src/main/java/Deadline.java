/**
 * Deadline task class
 */
public class Deadline extends Task{
    protected String by;

    /**
     * @param description name of deadline task to be added
     * @param by due date of deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /** @return the task type, completion status, name of deadline and due by when */
    public String toString() {
        return "[D] " + getStatusIcon() + " " + super.toString() + " (by: " + by + ")";
    }
}
