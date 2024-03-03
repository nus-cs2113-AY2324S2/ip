package nick.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task with its deadline.
 */
public class Deadline extends Task {
    protected String symbol = "D";
    protected String deadline;
    protected LocalDate deadlineDate;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;

        try {
            deadlineDate = LocalDate.parse(deadline);
        }
        catch (DateTimeParseException ignored) {
        }
    }

    public Deadline(String description, String deadline, boolean taskDone) {
        super(description);
        this.deadline = deadline;
        super.isDone = taskDone;

        try {
            deadlineDate = LocalDate.parse(deadline);
        }
        catch (DateTimeParseException ignored) {
        }
    }

    @Override
    public String toString() {
        return "\t" + "[" + symbol + "]" + "[" + super.getStatusIcon() + "] "
                + description + " (by: " + getDate() + ")";
    }

    public String getDeadline() {
        return deadline;
    }

    /**
     * Return the deadline object's deadline in MMM dd yyy format.
     * If the format is not possible, return the deadline as a string.
     *
     * @return Deadline in MMM dd yyyy format.
     */
    public String getDate() {
        try {
            return deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (NullPointerException e) {
            return this.deadline;
        }
    }
}
