package Tony.task;

public class Event extends Task {
    public String from;
    public String to;

    /**
     * Represents a {@code Event} object that saves task type <code>event</code>
     * @param description is the String <code>description</code> for event tasks
     * @param from is the String to specify event starts <code>from</code> time/date
     * @param to is the String to specify time/date that the event is <code>to</code>
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from.trim();
        this.to = to.trim();
    }

    /**
     * Returns the type of tasks to identify event.
     * @return String <code>[E]</code> as task type.
     */
    public String type() {
        return "[E]";
    }

    /**
     * Returns the String to save in the current task list
     * @return String formatted to be saved in task list.
     */
    @Override
    public String toString() {
        return type() + super.toString()
                + " (from: " + from
                + " to: " + to + ")";
    }
}
