package tasks;

/**
 * Represents a Deadline that has been completed or to be completed.
 */
public class Deadline extends Task {

    /**
     * Character Symbol which represents the task type
     */
    private String taskType = "D";
    /**
     * Ending DateTime of the event
     */
    private String endDate;
    /**
     * Constructs a Deadline object with the given description, and end date.
     * @param description Description of the deadline
     * @param date Ending DateTime of the deadline
     */
    public Deadline(String description, String date){
        super(description);
        this.endDate = date;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String date) {
        endDate = date;
    }

    /**
     * Converts the deadline to its string representation.
     * @return The string representation of the deadline
     */
    public String toString() {
        return "[" + this.getTaskType() + "]" + super.toString() + (" (by: " + this.getEndDate() + ")");
    }
}
