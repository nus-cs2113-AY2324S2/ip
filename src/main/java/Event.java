public class Event extends Task {
    protected String type = "E";
    protected String startTime;
    protected String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return String.format("%s %s (%s %s)", super.toString(), super.description, startTime, endTime);
    }
}
