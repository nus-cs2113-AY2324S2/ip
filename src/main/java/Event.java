/**
 * Event task class
 */
public class Event extends Task {
    String from;
    String to;

    /**
     * @param description name of event task to be added
     * @param from starting period of event
     * @param to ending period of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /** @return the task type, completion status, name of event, starting period and ending period */
    public String toString() {
        return "[E] " + getStatusIcon() + " " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
