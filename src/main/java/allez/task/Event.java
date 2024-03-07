package allez.task;

/**
 * Event are tasks that have a specific start and end timing, noted as /from and /to.
 * Inherits Task Class.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the task.
     *
     * @return /from of the task
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the task.
     *
     * @return /to of the task
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns all the details of the task as a string.
     *
     * @return task type, task status, task description, /from, /to
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to +")";
    }
}
