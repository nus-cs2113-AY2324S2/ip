package tasks;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String from, String to) {
        super();
        this.from = from;
        this.to = to;
    }

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(String name, boolean isDone, String from, String to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")");
    }
}
