/**
 * Event class represents a task with a specific event duration, extending the Task class.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event class.
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
     * Returns a formatted string representation of the Event task.
     *
     * @return Formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a formatted string representation of the Event task for saving to a file.
     *
     * @return Formatted string representing the Event task for file storage.
     */
    @Override
    public String toFileString() {
        return "E|" + super.toFileString() + "|" + from + "|" + to;
    }
}
