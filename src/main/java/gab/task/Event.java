package gab.task;

/**
 * Event class that creates a event task with name, start date and end date
 */
public class Event extends Task {
    public static final String EVENT_STATUS = "[E]";
    private final String START_TIME;
    private final String END_TIME;

    /**
     * Initialise new event task with name, start date and end date
     *
     * @param description event name
     * @param startTime event start date
     * @param endTime event end date
     */
    public Event (String description, String startTime, String endTime) {
        super(description);
        this.START_TIME = startTime;
        this.END_TIME= endTime;
    }

    /**
     * Returns format of event tasks to be printed
     *
     * @return string format of event task to be printed
     */
    @Override
    public String toString() {
        return EVENT_STATUS + super.toString() + " " + "(from: " + START_TIME + " to: " + END_TIME + ")";
    }

    /**
     * Convert event task to a file format to be displayed in external data file
     *
     * @return format of event task to be display
     */
    @Override
    public String toFileFormat() {
        return EVENT_STATUS + super.toFileFormat() + " | " + "from: " + START_TIME + " to: " + END_TIME;
    }
}
