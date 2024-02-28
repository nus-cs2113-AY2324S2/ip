package carrot.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " || to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        // Escape characters: usertyped " would not interfere with file format
        String escapedFrom = from.replace("\"", "\\\"");
        String escapedTo = to.replace("\"", "\\\"");

        return "E" + super.toFileFormat() + " | \"" + escapedFrom + "\" | \"" + escapedTo + "\"";
    }
}
