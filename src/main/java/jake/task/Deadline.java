package jake.task;
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a Deadline object based on user inputs.
     *
     * @param description Description of the task.
     * @param by Date to complete task by.
     * @param isDateFormatted True if date-time inputted is already formatted as MMM DD YYYY. Else, false.
     */
    public Deadline(String description, String by, boolean isDateFormatted) {
        super(description.substring(9));
        this.by = isDateFormatted ? by : convertDateTime(by);
    }

    /**
     * Prints the completion status of task, and description of the task.
     *
     * @return description of the task and completion status.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
