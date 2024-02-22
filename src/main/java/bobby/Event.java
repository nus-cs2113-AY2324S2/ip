package bobby;

public class Event extends Task {
    protected String by;
    protected String from;

    public Event(String description, String by, String from) {
        super(description);
        this.by = by;
        this.from = from;
    }

    public String getBy() {
        return by;
    }

    public String getFrom() {
        return from;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + by + ")";
    }
}
