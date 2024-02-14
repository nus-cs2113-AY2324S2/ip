public class Event extends Task {
    public static final String EVENT_STATUS = "[E]";
    private String startTime;
    private String endTime;

    public Event (String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return EVENT_STATUS + super.toString() + " " + "(from: " + startTime + " to: " + endTime + ")";
    }
}
