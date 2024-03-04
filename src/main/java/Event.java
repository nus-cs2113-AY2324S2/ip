/**
 * Represents an event task with a start and end time.
 * An {@code Event} object encapsulates the details of a task that occurs over a period defined by a start and end time.
 */
public class Event extends Task {
    protected String from; // The start time of the event.
    protected String to; // The end time of the event.

    /**
     * Constructs an {@code Event} with the specified task description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description); // Call the superclass constructor to set the task description.
        this.from = from; // Set the start time of the event.
        this.to = to; // Set the end time of the event.
    }

    /**
     * Returns the string representation of the event task in a format suitable for file storage.
     * The format is "E | isDone | description | from to to", where "E" indicates an event task.
     *
     * @return A string representation of the event task for file storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s to %s", isDone ? 1 : 0, description, from, to);
    }

    /**
     * Returns the string representation of the event task, including its status (done or not done),
     * description, and the period over which it occurs (from start time to end time).
     *
     * @return A string representation of the event task, including status, description, and period.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}

