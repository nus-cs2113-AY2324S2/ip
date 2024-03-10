/**
 * Represents a Deadline task with a specific deadline.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline of the Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including the deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
