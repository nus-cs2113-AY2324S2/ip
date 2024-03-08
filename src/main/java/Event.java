/**
 * Represents an event-type task in the task list.
 * An event task includes a description, start time and end time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new Event instance with the specified description, start time and end time.
     * @param description The text description of the event task.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    /**
     * Returns a string representation of the event task, including its status,
     * description and start and end timings.
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + getDescription() + " (from: " + start + " to: " + end + ")";
    }
}
