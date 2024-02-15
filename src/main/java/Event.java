public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event(String description, String startDateEntered, String endDateEntered) {
        super(description);
        startDateEntered = startDateEntered.replace("from", "from:");
        endDateEntered = endDateEntered.replace("to", "to:");
        this.startDate = startDateEntered;
        this.endDate = endDateEntered;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.startDate + this.endDate + ")";
    }
}
