package tasks;

/**
 * Event subclass of Task. Requires a from and to input.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for the event object.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for event when restructuring the list from
     * nocturne.txt, which includes the completion status of the event.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone The boolean variable of whether the event is done or not.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Override of the event class when printed,
     * resulting in the format shown in the list.
     *
     * @return The string that will be printed.
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() +
                "] " + this.description +
                " (from: " + this.from + " to: " + this.to + ")";
    }
}
