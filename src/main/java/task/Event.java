package task;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String From, String to) {
        super(description);
        this.from = From;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String From) {
        this.from = From;
    }

    @Override
    public String toString() {
        String isDoneIcon = super.isDone ? "X" : " ";
        return "[E][" + isDoneIcon + "] " + super.description + " (from: " + from + " to: " + to + ")";
    }
}

