public class Event extends Task {
    protected String from;
    protected String to;

    public String toFileFormat() {
        return String.format("E | %d | %s | %s to %s", isDone ? 1 : 0, description, from, to);
    }
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
