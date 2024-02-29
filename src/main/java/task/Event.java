package carrot.task;

/**
 * Represents an event task, a type of task requires a description, a specific start and end time.
 * <p>
 * An event task inherits properties and behaviors from the {@link Task} class.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an event task with the specified description, start time, and end time.
     *
     * @param description the description of the event task
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an event task with the specified description, start time, end time, and completion status.
     *
     * @param description the description of the event task
     * @param from        the start time of the event
     * @param to          the end time of the event
     * @param isDone      the completion status of the event task
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     * <p>
     * The string includes the task type indicator "[E]" followed by the status icon,
     * description, start time, and end time of the event task.
     *
     * @return a string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " || to: " + to + ")";
    }

    /**
     * Returns the event task in a format suitable for storing in a file.
     * <p>
     * The event task is formatted with the task type indicator "E" followed by the format of the parent task,
     * start time, and end time.
     * <p>
     * Any double quotes in the start time and end time are escaped to prevent interference
     * with the file format.
     *
     * @return the event task in file format
     */
    @Override
    public String toFileFormat() {
        // Escape characters: usertyped " would not interfere with file format
        String escapedFrom = from.replace("\"", "\\\"");
        String escapedTo = to.replace("\"", "\\\"");

        return "E" + super.toFileFormat() + " | \"" + escapedFrom + "\" | \"" + escapedTo + "\"";
    }
}
