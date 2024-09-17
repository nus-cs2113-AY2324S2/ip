/**
 * The EventTask class represents a task that is an event with a start time and an end time.
 * It extends the Task class.
 */
public class EventTask extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an EventTask object with the provided description, start time, and end time.
     * @param description The description of the event task
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the description of the event task.
     * @return The description of the event task
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Gets the start time of the event task.
     * @return The start time of the event task
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event task.
     * @return The end time of the event task
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task.
     * @return A string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
