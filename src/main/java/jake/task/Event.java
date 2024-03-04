package jake.task;
public class Event extends Task {

    protected String startDate;
    protected String endDate;

    public Event(String description, String startDate, String endDate, boolean isDateFormatted) {
        super(description.substring(6));
        this.startDate = isDateFormatted ? startDate : convertDateTime(startDate);
        this.endDate = isDateFormatted ? endDate : convertDateTime(endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
