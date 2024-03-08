package jake.task;

import java.time.format.DateTimeParseException;
import jake.JakeException;

public class Event extends Task {
    protected String startDate;
    protected String endDate;

    /**
     * Creates an Event object based on user inputs.
     *
     * @param description Description of the task.
     * @param startDate Date during which task starts.
     * @param endDate Date during which task ends.
     * @param isDateFormatted True if date-time inputted is already formatted as MMM DD YYYY. Else, false.
     * @throws JakeException if date-time format does not comply
     */
    public Event(String description, String startDate, String endDate, boolean isDateFormatted) throws JakeException {
        super(description.substring(6));
        try {
            this.startDate = isDateFormatted ? startDate : convertDateTime(startDate);
            this.endDate = isDateFormatted ? endDate : convertDateTime(endDate);
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
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
