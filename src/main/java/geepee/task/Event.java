package geepee.task;

public class Event extends Task {

    /** Start of event task */
    private String from;
    /** End of event task */
    private String to;

    /**
     * Initialises an instance of the Event class with completion status as false.
     * Calls the Task constructor to initialise the task description and completion status.
     * 
     * @param description Description of event task.
     * @param from Start of event task.
     * @param to End of event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Initialises an instance of the Event class with a given completion status.
     * Calls the Task constructor to initialise the task description and completion status.
     * 
     * @param description Description of event task.
     * @param from Start of event task.
     * @param to End of event task.
     * @param isDone Completion status of task.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    public String toString() {
        return String.format("[E]" + super.toString() + " (from: " + from + " to: " + to + ")");
    }

    /**
     * Returns a string containing details of the event task for storage in the data file.
     */
    public String toFileFriendlyString() {
        return String.format("E;" + super.toFileFriendlyString() + ";" + from + ";" + to);
    }
}
