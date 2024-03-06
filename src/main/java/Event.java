/**
 * Represents an event task in the Phoebe application.
 * An event task is a task that occurs over a period of time, defined by a start and end time.
 */
public class Event extends Task {

    protected String from; // The start time of the event
    protected String to;   // The end time of the event

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event as a String.
     * @param to The end time of the event as a String.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task, including its type, status,
     * description, and the period over which it occurs.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the event task into a format suitable for file storage.
     * The format includes the task type, completion status, description, and the event's period.
     *
     * @return A string representation of the event task in a file storage format.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s to %s", this.isDone ? 1 : 0, this.description, this.from, this.to);
    }
}
