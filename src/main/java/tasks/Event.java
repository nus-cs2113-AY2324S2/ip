package tasks;


/**
 * Represents an Event that has been completed or to be completed.
 */
public class Event extends Task {

    /**
     * Character Symbol which represents the task type
     */
    private String taskType = "E";
    /**
     * Starting DateTime of the event
     */
    private String startDate;
    /**
     * Ending DateTime of the event
     */
    private String endDate;

    /**
     * Constructs an Event object with the given description, startDate and endDate.
     * @param description Description of the event
     * @param startDate Starting DateTime of the event
     * @param endDate Ending DateTime of the event
     */
    public Event(String description, String startDate, String endDate){
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Converts the event to its string representation.
     * @return The string representation of the event
     */
    public String toString() {
        return "[" + this.getTaskType() + "]" + super.toString()
                + " (from: " +this.getStartDate() + " to: " + this.getEndDate() + " )";
    }

}
