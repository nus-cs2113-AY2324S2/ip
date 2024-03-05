package tasks;

/**
 * Represents an event task in KikuBot.
 * An event task has a start and end date/time along with a description.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new Event instance with the specified description,
     * start date/time, and end date/time.
     *
     * @param description The description of the event task.
     * @param start The start date/time of the event.
     * @param end The end date/time of the event.
     */
    public Event (String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task formatted for printing,
     * including the type of the task, its status, description, and its start and end times.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns a string representation of the event task formatted for file storage,
     * inlcuding the type of the task, its done status, description, start time, and end times,
     * separated by a pipe character.
     *
     * @return A string formatted for saving the event task to a file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
