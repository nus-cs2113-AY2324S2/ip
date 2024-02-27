package misty.data.task;

public class Event extends Task {
    private String from;
    private String to;

    public Event (String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatus() + " " + getTaskName() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
