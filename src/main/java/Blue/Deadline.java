package Blue;

/**
 * Represents a task that needs to be completed by a certain deadline.
 */
public class Deadline extends Task {
    private String by;

    /**
     * A constructor for new deadline object.
     *
     * @param description A description of the task.
     * @param by When it needs to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of a Deadline task.
     *
     * @return The deadline of a task in String format.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toSaveTextFormat() {
        String done = (getStatusIcon().equals("X") ? "1" : "0");
        return "D|" + done + "|" + getDescription() + "|" + by + System.lineSeparator();
    }

    @Override
    public String toString() {
        return super.toString() + " by: " + by;
    }
}
