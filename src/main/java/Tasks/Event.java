package Tasks;

public class Event extends Task{
    protected String startDate;
    protected String endDate;

    public Event(String content, boolean isDone, String from, String to) {
        super(content, isDone);
        this.startDate = from;
        this.endDate = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + "to: " + endDate + ")";
    }
}
