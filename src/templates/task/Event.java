package templates.task;

import templates.BaseDate;

public class Event extends Task {
    private BaseDate startDateTime = null;
    private BaseDate endDateTime = null;
    public static String KEYWORD_START = "/from";
    public static String KEYWORD_END = "/to";

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

    public BaseDate getEndDateTime() {
        return this.endDateTime;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)", startDateTime.toString(), endDateTime.toString());
    }
}
