package Blue;

/**
 * Represents an event that occurs within a certain time.
 */
public class Event extends Deadline {
    private String from;

    /**
     * A constructor for new Event object.
     *
     * @param description A description of the event.
     * @param from The start of the event.
     * @param to The end of the event.
     */
    public Event(String description, String from, String to) {
        super(description, to);
        this.from = from;
    }

    @Override
    public String toSaveTextFormat() {
        String done = (getStatusIcon().equals("X") ? "1" : "0");
        return "E|" + done + "|" + this.getDescription() + "|" + this.getBy() + "|" + from + System.lineSeparator();
    }

    @Override
    public String toString() {
        return this.getDescription() + " from " + from + " to " + this.getBy();
    }
}
