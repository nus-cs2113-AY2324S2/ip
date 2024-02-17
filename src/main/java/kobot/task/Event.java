package kobot.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event (String description, String from, String to, Boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return String.format("[E]%s %s (from: %s, to: %s)", this.getStatusIcon(), this.description, this.from, this.to);
    }

    public String toStorageFormat() {
        return String.format("E;%b;%s;%s;%s", this.isDone, this.description, this.from, this.to);
    }
}
