package task;

import ui.Time;

/**
 * The Deadline class represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the deadline.
     * @param by The date/time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = Time.standardize(by);
        } catch (IllegalArgumentException e) {
            this.by = by;
        }
    }

    /**
     * Returns the string representation of the Deadline object.
     *
     * @return The string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
