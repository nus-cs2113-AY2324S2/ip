/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    /** The deadline date for the task. */
    protected String deadlineDate;

    /**
     * Constructs a Deadline object with the specified description.
     *
     * @param description The description of the deadline task.
     */
    public Deadline(String description) {
        super(description);
        taskType = "D";
        int indexOfDivider = description.indexOf("/by:");

        if (indexOfDivider == -1) {
            this.description = description;
            setDeadlineDate(null);
        } else {
            String endDate = description.substring(indexOfDivider + 3);
            String descriptionWithoutDate = description.substring(0, (indexOfDivider)).replace("deadline", "").trim();

            setDeadlineDate(endDate);
            this.description = descriptionWithoutDate + " (by" +getDeadlineDate().trim() + ")";
        }
    }

    /**
     * Returns the deadline date of the task.
     *
     * @return The deadline date of the task.
     */
    public String getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Sets the deadline date of the task.
     *
     * @param deadlineDate The deadline date to be set.
     */
    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns the string representation of the task for saving.
     *
     * @return The string representation of the task for saving.
     */
    @Override
    public String saveTaskRepresentation() {
        return "D|" + super.saveTaskRepresentation()  + "|" + deadlineDate; // Prefix with "D" to indicate Deadline
    }
}
