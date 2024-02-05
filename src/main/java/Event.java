public class Event extends Task {
    protected String from;
    protected String to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return String.format("[E]%s %s (from: %s, to: %s)", this.getStatusIcon(), this.description, this.from, this.to);
    }
}
