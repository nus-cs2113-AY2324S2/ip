/**
 * Represents an Event task with a specific start and end date/time.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates a new Event task with the given description, start, and end date/time.
     *
     * @param description The description of the Event task.
     * @param from        The start date/time of the Event task.
     * @param to          The end date/time of the Event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including start and end date/time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }
}
