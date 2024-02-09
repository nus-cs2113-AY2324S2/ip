public class Event extends Task {
    String from;
    String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E] " + getStatusIcon() + " " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
