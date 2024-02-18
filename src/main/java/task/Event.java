package task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return "event/" + this.isDone + "/" + this.description + "/" + start + "/" + end;
    }

    public String getDescription() {
        return "EVENT: " + description + " (" + this.start + " to " + this.end + ")";
    }
}
