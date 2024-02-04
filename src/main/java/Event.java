public class Event extends Task {
    protected static final String EVENT_SYMBOL = "[E]";
    protected String startDate;
    protected String endDate;

    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskStatus() {
        return EVENT_SYMBOL + super.getTaskStatus() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
