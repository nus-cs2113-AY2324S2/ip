package cody.tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return description + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
