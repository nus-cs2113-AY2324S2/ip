package gab.task;

public class Event extends Task {
    public static final String EVENT_STATUS = "[E]";
    private final String START_TIME;
    private final String END_TIME;

    public Event (String description, String startTime, String endTime) {
        super(description);
        this.START_TIME = startTime;
        this.END_TIME= endTime;
    }

    @Override
    public String toString() {
        return EVENT_STATUS + super.toString() + " " + "(from: " + START_TIME + " to: " + END_TIME + ")";
    }

    @Override
    public String toFileFormat() {
        return EVENT_STATUS + super.toFileFormat() + " | " + "from: " + START_TIME + " to: " + END_TIME;
    }
}
