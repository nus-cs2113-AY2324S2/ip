/**
 * Represents an event task, which is a type of task that occurs within a specific time range.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a new event task with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param start       The start time of the event task.
     * @param end         The end time of the event task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a new event task with the given description, start time, end time, and completion status.
     *
     * @param description The description of the event task.
     * @param start       The start time of the event task.
     * @param end         The end time of the event task.
     * @param isDone      The completion status of the event task.
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description);
        this.start = start;
        this.end = end;
        if (isDone) {
            markAsDone();
        }
    }

    /**
     * Returns a string representation of the event task, including its completion status, description, start time, and end time.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + start + " To: " + end + ")";
    }

    /**
     * Returns a string representation of the event task for saving to a file, including its completion status, description, start time, and end time.
     *
     * @return A string representing the event task for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + start + " | " + end;
    }
}
