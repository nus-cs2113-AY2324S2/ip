package bobby;

/**
 * Represents an event task, which is a type of task.
 */
public class Event extends Task {
    protected String by;
    protected String from;

    /**
     * Constructs a new Event instance with the given description, completion status, start time, and end time.
     *
     * @param description The description of the event task.
     * @param isDone Indicates whether the event task is done or not.
     * @param by The end time of the event task.
     * @param from The start time of the event task.
     */
    public Event(String description, Boolean isDone, String by, String from) {
        super(description, isDone);
        this.by = by;
        this.from = from;
    }

    /**
     * Gets the end time of the event task.
     *
     * @return The end time of the event task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Gets the start time of the event task.
     *
     * @return The start time of the event task.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task, including its status icon, description,
     * start time, and end time, prefixed with "[E]".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + by + ")";
    }
}
