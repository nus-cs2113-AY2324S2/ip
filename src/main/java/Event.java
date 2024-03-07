/**
 * The Event class represents a task that occurs within a specific time range.
 * It extends the Task class and includes functionality for storing the event's start and end times.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an Event object with the given description, start time, end time, and completion status.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isDone      The completion status of the event task.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Returns the type identifier for the Event task, which is "[E]".
     *
     * @return The type identifier for the Event task.
     */
    public String getType() {
        return "[E]";
    }

    /**
     * Returns a string representation of the Event object, including the start and end times.
     *
     * @return A string representation of the Event object with start and end times.
     */
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Event object in a format suitable for saving to a file.
     *
     * @return A string representation of the Event object in a format suitable for saving to a file.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}