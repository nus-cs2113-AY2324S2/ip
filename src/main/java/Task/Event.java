package task;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private final String start;
    private final String end;

    /**
     * Creates a new Event task.
     *
     * @param description The description of the event.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time.
     */
    public String getStart() {
        return start;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time.
     */
    public String getEnd() {
        return end;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String saveString() {
        return getType() + " | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + getStart() + " - " + getEnd();
    }

}