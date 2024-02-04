public class Event extends Task {
    private final String startTime;
    private final String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime.split("from ", 2)[1];
        this.endTime = endTime.split("to ", 2)[1];
    }
    public String getTypeDisplay() {
        return "[E]";
    }

    public String getData() {
        return this.getTypeDisplay() + this.getMarkDisplay() + " " + this.getDescription()
                + "(from: " + this.startTime + "to: " + this.endTime + ")";
    }
}
