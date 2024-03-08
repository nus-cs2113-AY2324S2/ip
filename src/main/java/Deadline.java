/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    /**
     * Date of the deadline.
     */
    protected String dateOfDeadline;

    /**
     * Constructs a Deadline task with the given description.
     * If the description contains a deadline date, it is extracted and stored.
     * Otherwise, the description is used directly.
     *
     * @param description The description of the task.
     */
    public Deadline(String description) {
        super(description);
        typeOfTask = "D";
        int dividerIndex = description.indexOf("by");

        if (dividerIndex == -1) {
            this.description = description;
            setDateOfDeadline(null);
        } else {
            String endDate = description.substring(dividerIndex + 3);
            String descriptionWithoutDate = description.substring(0, (dividerIndex - 1)).replace("deadline", "");
            setDateOfDeadline(endDate);
            this.description = descriptionWithoutDate + " (by: " + getDateOfDeadline() + ")";
        }
    }

    /**
     * Gets the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public String getDateOfDeadline() {
        return dateOfDeadline;
    }

    /**
     * Sets the date of the deadline.
     *
     * @param dateOfDeadline The date of the deadline.
     */
    public void setDateOfDeadline(String dateOfDeadline) {
        this.dateOfDeadline = dateOfDeadline;
    }

    /**
     * Converts the Deadline task to a string suitable for storage in a file.
     *
     * @return A string representation of the Deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return "D|" + super.toFileString() + "|" + dateOfDeadline; // Prefix with "D" to indicate Deadline
    }
}