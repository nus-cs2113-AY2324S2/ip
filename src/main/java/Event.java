/**
 * This class represents an event task with a start and end time.
 * It inherits from the `Task` class and adds start and end time fields.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for the Event class.
     *
     * @param description the description of the task
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Overrides the `toString` method from the `Task` class to provide a specific
     * string representation for event tasks, including the start and end time.
     *
     * @return the string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + super.getDescription() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Implements the `toFileString` method from the `Task` class to provide a string
     * representation suitable for storing the event task in a file.
     *
     * @return the string representation of the event task for file storage
     */
    @Override
    public String toFileString() {
        return "[E]" + super.getStatusIcon() + " " + super.getDescription() + " (from: " + from + " to: " + to + ")";
    }
}
