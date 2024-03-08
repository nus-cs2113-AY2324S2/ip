public class Event extends Task {
    protected String taskType;
    protected String from;
    protected String to;

    /**
     * Constructs a new Event object with the given description, start and end time.
     *
     * @param description The description of the event
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "E";
    }

    /**
     * Returns the task type of the event
     * Overrides the toString method in Task
     * Indicates the event time
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
