package ip.task;

public class Event extends Task {
    private static final int TASK_START_INDEX = 6;
    private String from;
    private String to;

    public Event(String line) {
        super(line.substring(TASK_START_INDEX, line.indexOf("/from")));
        from = line.substring(line.indexOf("/from") + 6, line.indexOf("/to"));
        to = line.substring(line.indexOf("/to") + 4);
    }

    public Event(boolean isDone, String description, String from, String to) {
        super(description);
        this.isDone = isDone;
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDetails() {
        return ("[E]" + super.getDetails() + "(from: " + from + ", to: " + to + ")");
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
