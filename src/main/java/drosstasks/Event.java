package drosstasks;

/**
 * Represents an event task with a specific start and end time.
 * Inherits from the {@link Task} class, adding support for event duration.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including its status, description,
     * and time frame.
     *
     * @return A formatted string indicating the event's details.
     */
    @Override
    public String toString() {
        String statusMark = this.isCompleted() ? "x" : " "; // Mark with 'x' if completed
        return "[E]" + "[" + statusMark + "] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
