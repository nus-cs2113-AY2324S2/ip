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
        return "[E][" + getStatusIcon() + "] " + description + "(from: " + eventStart + " to: " + eventEnd + ")";
    }
}
