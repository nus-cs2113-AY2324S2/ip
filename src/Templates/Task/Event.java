package Templates.Task;

import Templates.BaseDate;

public class Event extends Task {
    private BaseDate startDateTime = null;
    private BaseDate endDateTime = null;
    public static String keyword1 = "/from";
    public static String keyword2 = "/to";

    public Event(String args, BaseDate startDateTime, BaseDate endDateTime) throws Exception {
        super(args, "E", "Event");
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;

    }

    public void setDate(BaseDate newStartDate, BaseDate newEndDate){
        assert newStartDate != null : "Start date is null!";
        assert newEndDate != null : "End date is null!";

        this.startDateTime = newStartDate;
        this.endDateTime = newEndDate;
    }

    public BaseDate getStartDateTime() {
        return this.startDateTime;
    }

    public BaseDate getEnDateTime() {
        return this.endDateTime;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("(from: %s to: %s)", startDateTime.toString(), endDateTime.toString());
    }
}
