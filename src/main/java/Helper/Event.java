package Helper;

/**
 * The Event class represents a task that occurs within a specific time frame.
 * It inherits from the Task class and adds functionality specific to Event tasks.
 */

public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an Event object with the given description and time frame.
     *
     * @param description Description of the task.
     * @param from The starting time of the Event.
     * @param to The ending time of the Event.
     */

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event object.
     * The representation includes the task type identifier, description, and timeframe.
     *
     * @return A string representation of the Event object.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Event object in a format suitable for file storage.
     * The representation includes the task type identifier, status, description, and timeframe.
     *
     * @return A string representation of the Event object for file storage.
     */

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
