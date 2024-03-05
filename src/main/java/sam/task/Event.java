package sam.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + description + " (" + start + " to " + end + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
