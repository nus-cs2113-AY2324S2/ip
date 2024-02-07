public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        startDate = startDate.replace("from", "from:");
        endDate = endDate.replace("to", "to:");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.startDate + this.endDate + ")";
    }
}
