package Yoj.tasks;
public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String taskType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return taskType() + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}