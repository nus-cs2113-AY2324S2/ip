package mona.task;

/**
 * Represents an event task with a description, start date/time, and end date/time. The Event class
 * is a subclass of the Task class.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    /**
     * Constructor for Event. Initializes the task with a description, start date/time, and end date/time.
     *
     * @param description The description of the event task.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns a string representation of the event task, including its type, status, description,
     *  start date/time, and end date/time.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " +  "(from: " + this.from + " to: " + this.to + ")";
    }
    /**
     * Returns the full description of the event task, including its description, start date/time, and end date/time.
     *
     * @return The full description of the event task.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " "
                + "/from " + this.from + " "
                + "/to " + this.to;
    }
}
