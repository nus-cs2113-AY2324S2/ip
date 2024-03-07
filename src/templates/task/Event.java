package templates.task;

import templates.BaseDate;

/**
 * Abstract base class and its subclasses representing different types of tasks within the Mario application.
 * Task is the base class providing common properties such as task description and completion status.
 * Event, Deadline, and ToDo extend Task to implement specific behaviors and properties relevant to their respective types,
 * such as start and end dates for Event, a deadline date for Deadline, and basic task properties for ToDo.
 */

 
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
