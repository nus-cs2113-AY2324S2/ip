package vibes.task.type;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private final static String TASK_TYPE = "E";
    public static final String PRINT_FORMAT = "[E]%s (from: %s to: %s)";
    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the given description, starting date, and ending date.
     *
     * @param description the description of the event
     * @param from        the starting date of the event
     * @param to          the ending date of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the starting date of the event.
     *
     * @return the starting date of the event
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the starting date of the event.
     *
     * @param from the starting date to be set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets the ending date of the event.
     *
     * @return the ending date of the event
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the ending date of the event.
     *
     * @param to the ending date to be set
     */
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, super.toString(), from, to);
    }
}
