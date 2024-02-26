package natsu.task;

/**
 * Represents an event task with a specific start and end time.
 * An event task is a type of task that occurs over a period, defined by a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event, represented as a string.
     * @param to          The end time of the event, also represented as a string.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including its status,
     * description, start time, and end time.
     *
     * @return A string in the format "[E][status] description (from: start time to: end time)",
     * where "[E]" indicates an event task, "[status]" is the task's completion status
     * (marked as done or not done), "description" is the task's description,
     * and "start time" and "end time" are the event's start and end times, respectively.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
