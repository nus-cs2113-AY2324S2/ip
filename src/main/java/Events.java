/**
 * The Events class represents a task that spans over a period of time.
 */
public class Events extends Task {
    /** The start time of the event. */
    protected String from;
    /** The end time of the event. */
    protected String to;

    /**
     * Constructs an Events object with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isDone      The completion status of the event task.
     */
    public Events(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E] " + super.getStatusIcon() + " " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
