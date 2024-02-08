public class Event extends Task {
    protected String startDayAndTime;
    protected String endDayAndTime;

    public Event (String description, String startDayAndTime, String endDayAndTime) {
        super(description);
        this.startDayAndTime = startDayAndTime;
        this.endDayAndTime = endDayAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDayAndTime + " to: " + endDayAndTime + ")";
    }
}
