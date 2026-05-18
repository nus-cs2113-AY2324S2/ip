/**
 * Represents an event task in Duke.
 *
 * An Event object has information about an event task, including its description,
 * start date, end date, and completion status. It provides methods to access and manipulate
 * these attributes.
 *
 * @param description the description of the event task
 * @param from the start date/time of the event
 * @param to the end date/time of the event
 * @return an Event object with the specified description, start date, and end date
 * @throws NullPointerException if the description, start date, or end date is null
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}
