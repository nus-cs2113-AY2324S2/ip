package ip.task;

/**
 * A subclass of the Task class. Contains the additional information
 * of when the event is from and to
 */
public class Event extends Task {
    private static final int TASK_START_INDEX = 6;
    private String from;
    private String to;

    /**
     * Constructor to process the user's input and call the constructor of the
     * superclass with the appropriate information of the task's description passed.
     * Also extracts from the user's input when the event is from and to
     * and assigns to the respective attributes
     *
     * @param line the user's input
     */
    public Event(String line) {
        super(line.substring(TASK_START_INDEX, line.indexOf("/from")));
        from = line.substring(line.indexOf("/from") + 6, line.indexOf("/to"));
        to = line.substring(line.indexOf("/to") + 4);
    }

    /**
     * Overloaded constructor that takes in the information needed for the object's
     * attributes directly instead of the user's input.
     * Calls the constructor of the superclass with the description passed
     * and assigns the isDone, from, and to attributes
     *
     * @param isDone whether the event is done
     * @param description the description of the event
     * @param from when the event is from
     * @param to when the event is to
     */
    public Event(boolean isDone, String description, String from, String to) {
        super(description);
        this.isDone = isDone;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted String containing the icon that represents the
     * task type (Event), the status icon, and the description of the task
     *
     * @return a String specifying the object is an Event, if it is done, and its description
     */
    @Override
    public String getDetails() {
        return ("[E]" + super.getDetails() + "(from: " + from + ", to: " + to + ")");
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
