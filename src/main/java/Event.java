/**
 * Tasks of the Event type, which extends Task by adding a "start" and "end" variable.
 */
public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * @return task type for representation in list form.
     */
    public String type() {
        return "[E]";
    }

    /**
     * @return String for representation in list form.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
