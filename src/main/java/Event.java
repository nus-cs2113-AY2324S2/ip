public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    public String getType() {
        return "[E]";
    }

    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}