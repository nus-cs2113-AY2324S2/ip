package jeff.tasks;

import jeff.Task;

/**
 * Represents an event task.
 * Extends the Task class and adds functionality specific to event tasks,
 * such as storing the event start and end times.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     *
     * @param description Description of the event task.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return String representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }

    /**
     * Returns a string representation of the event task suitable for storing in a file.
     *
     * @return String representing the event task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + from.trim() + "-" + to.trim();
    }
}
