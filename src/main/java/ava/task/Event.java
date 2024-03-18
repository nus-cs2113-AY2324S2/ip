package ava.task;

/**
 * Represents a task with a start date and an end date.
 */
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

    public Event(String description, String startDateEntered, String endDateEntered, boolean isCompleted) {
        super(description, isCompleted);
        startDateEntered = startDateEntered.replace("from", "from:");
        endDateEntered = endDateEntered.replace("to", "to:");
        this.startDate = startDateEntered;
        this.endDate = endDateEntered;
    }

    /**
     * Converts an Event object to a string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.startDate + this.endDate + ")";
    }
}
