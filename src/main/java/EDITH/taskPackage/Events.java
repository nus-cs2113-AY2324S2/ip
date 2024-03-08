package EDITH.taskPackage;

/**
 * Represents an event task, which is a type of task that occurs within a specific timeframe.
 */
public class Events extends Task {
    private String fromDate;
    private String toDate;

    /**
     * Constructs an Events object with the specified description,
     * start date, end date, and completion status.
     *
     * @param description The description of the event task.
     * @param fromDate    The start date of the event.
     * @param toDate      The end date of the event.
     * @param isDone      The completion status of the event task.
     */
    public Events(String description, String fromDate, String toDate, boolean isDone) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns the status icon of the event task, which is "[E]".
     *
     * @return The status icon of the event task.
     */
    @Override
    public String getStatusIcon() {

        return "[E]" + super.getStatusIcon();
    }

    /**
     * Returns the description of the event task along with its start and end dates.
     *
     * @return The description of the event task along with its start and end dates.
     */
    @Override
    public String getDescription() {

        return super.getDescription() + " (from: " + fromDate + " to: " + toDate + ")";
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date of the event.
     */
    public String getFromDate() {

        return fromDate;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date of the event.
     */
    public String getToDate() {

        return toDate;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
