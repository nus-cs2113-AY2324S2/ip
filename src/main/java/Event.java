/**
 * The Event class represents a task that spans a specific period.
 * It is a subclass of the Task class.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected String from;
    /** The end time of the event. */
    protected String to;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]"  +"[" + this.getStatusIcon()+ "] " + super.toString() + " (from: " + from + " to: " + to +")";
    }

    /**
     * Returns a formatted string representation of the Event object for saving to a file.
     *
     * @return A string representation of the Event object for saving to a file.
     */
    @Override
    public String toFileString() {
        return "E|" + super.toFileString() + "|" + from + "|" + to; // Prefix with "E" to indicate Event
    }
}
