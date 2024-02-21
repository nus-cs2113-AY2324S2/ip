package Tony.task;

public class Event extends Task {
    public String from;
    public String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from.trim();
        this.to = to.trim();
    }
    public String type() {
        return "[E]";
    }

    @Override
    public String toString() {
        return type() + super.toString()
                + " (from: " + from
                + " to: " + to + ")";
    }
}
