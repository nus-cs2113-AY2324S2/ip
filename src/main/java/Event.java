/**
 * Represents a specific type of task called "Event" in the Jane task management application.
 * It extends the base Task class and includes additional information about the event's start and end times.
 */
public class Event extends Task {
    /** The start time of the event. */
    protected String start;
    /** The end time of the event. */
    protected String end;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param start The start time information associated with the event.
     * @param end The end time information associated with the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Retrieves the start time information associated with the event.
     *
     * @return The start time information.
     */
    public String getStart() {
        return start;
    }

    /**
     * Retrieves the end time information associated with the event.
     *
     * @return The end time information.
     */
    public String getEnd() {
        return end;
    }

    /**
     * Generates a string representation of the Event task, including its specialized status icon and event information.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}