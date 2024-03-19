/**
 * Represents a task with an event duration.
 */
public class Event extends Task {

    /** The start date of the event. */
    protected String startDate;

    /** The end date of the event. */
    protected String endDate;

    /**
     * Constructs an Event object with the specified description.
     *
     * @param description The description of the event task.
     */
    public Event(String description) {
        super(description);
        taskType = "E";

        int indexOfFromDivider = description.indexOf("from:");
        int indexOfToDivider = description.indexOf("to:");

        setStartDate(description.substring(indexOfFromDivider + 5, indexOfToDivider - 1));
        setEndDate(description.substring(indexOfToDivider + 3));

        if (indexOfFromDivider == -1) {
            setStartDate(null);
        } else if (indexOfToDivider == -1) {
            setEndDate(null);
        }

        String descriptionWithoutDate = description.substring(0, (indexOfFromDivider - 1)).replace("event", "").trim();

        this.description = descriptionWithoutDate + " (from: " + getStartDate().trim() + " to: " + getEndDate().trim() + ")";

    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date of the event.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the event.
     *
     * @param startDate The start date to be set.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date of the event.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the event.
     *
     * @param endDate The end date to be set.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the string representation of the task for saving.
     *
     * @return The string representation of the task for saving.
     */
    @Override
    public String saveTaskRepresentation() {
        return "E|" + super.saveTaskRepresentation()+ "|" + startDate.trim() + "|" + endDate.trim(); // Prefix with "E" to indicate Event
    }
}
