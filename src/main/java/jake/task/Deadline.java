package jake.task;

import java.time.format.DateTimeParseException;
import jake.JakeException;

public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline object based on user inputs.
     *
     * @param description Description of the task.
     * @param by Date to complete task by.
     * @param isDateFormatted True if date-time inputted is already formatted as MMM DD YYYY. Else, false.
     * @throws JakeException if date-time format does not comply     
     */
    public Deadline(String description, String by, boolean isDateFormatted) throws JakeException {
        super(description.substring(9));
        
        try {
            this.by = isDateFormatted ? by : convertDateTime(by);
        } catch (DateTimeParseException e) {
            throw new JakeException("Invalid date and/or time format given. Using current date");
        }
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
