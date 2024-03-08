/**
 * Represents an event task with starting and ending dates.
 */
public class Event extends Task {

    /**
     * Starting date of the event.
     */
    protected String startingDate;

    /**
     * Ending date of the event.
     */
    protected String endingDate;

    /**
     * Constructs an Event object with the given description.
     * The description format should include "from" and "to" to specify starting and ending dates respectively.
     *
     * @param description Description of the event task.
     */
    public Event(String description) {
        super(description);
        typeOfTask = "E";

        int dividerIndexFrom = description.indexOf("from");
        int dividerIndexTo = description.lastIndexOf("to");

        setStartingDate(description.substring(dividerIndexFrom + 5, dividerIndexTo - 1));
        setEndingDate(description.substring(dividerIndexTo + 3));

        if (dividerIndexFrom == -1) {
            setStartingDate(null);
        } else if (dividerIndexTo == -1) {
            setEndingDate(null);
        }

        String descriptionWithoutDate = description.substring(0, (dividerIndexFrom - 1)).replace("event", "");

        this.description = descriptionWithoutDate + " (from: " + getStartingDate() + " to: " + getEndingDate() + ")";
    }

    /**
     * Gets the starting date of the event.
     *
     * @return The starting date of the event.
     */
    public String getStartingDate() {
        return startingDate;
    }

    /**
     * Sets the starting date of the event.
     *
     * @param startingDate The starting date to be set.
     */
    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * Gets the ending date of the event.
     *
     * @return The ending date of the event.
     */
    public String getEndingDate() {
        return endingDate;
    }

    /**
     * Sets the ending date of the event.
     *
     * @param endingDate The ending date to be set.
     */
    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    /**
     * Converts the Event object to a string suitable for writing to a file.
     *
     * @return A string representation of the Event object in file format.
     */
    @Override
    public String toFileString() {
        return "E|" + super.toFileString() + "|" + startingDate + "|" + endingDate; // Prefix with "E" to indicate Event
    }
}