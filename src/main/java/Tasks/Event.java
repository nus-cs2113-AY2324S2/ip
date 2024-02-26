package Tasks;

/**
 * The Event class represents an event task.
 * It extends the Task class and provides specific behavior for event tasks.
 */
public class Event extends Task{
    /** The start date of the event. */
    protected String startDate;

    /** The end date of the event. */
    protected String endDate;

    /**
     * Constructs a new Event object with the given content, completion status, start date, and end date.
     *
     * @param content   The content of the event task.
     * @param isDone    The completion status of the event task (true if completed, false otherwise).
     * @param from      The start date of the event.
     * @param to        The end date of the event.
     */
    public Event(String content, boolean isDone, String from, String to) {
        super(content, isDone);
        this.startDate = from;
        this.endDate = to;
    }

    /**
     * Returns a string representation of the event task.
     * The string representation includes the task type identifier "[E]", its content, start date, and end date.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
