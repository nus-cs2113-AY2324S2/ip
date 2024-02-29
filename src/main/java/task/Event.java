package task;

public class Event extends Task {
    private String eventStart;
    private String eventEnd;

    public Event(String description, String eventStart, String eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String getTaskDetails() {
        return "[E][" + this.getStatusIcon() + "] " + this.description + " (from: "
                + this.eventStart + " to: " + this.eventEnd + ")";
    }

    @Override
    public String getTaskAsString() {
        return "E | " + this.getDoneAsInteger() + " | " + this.description + " | "
                + this.eventStart + " " + this.eventEnd;
    }
}
