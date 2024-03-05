import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected String byString; // Stores the deadline in string format
    protected LocalDateTime byDateTime; // Stores the deadline in datetime format

    /**
     * Constructs a deadline task with the specified description and deadline.
     *
     * @param description the description of the task
     * @param by          the deadline of the task in string format
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            // Try to parse the deadline into a LocalDateTime object
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            this.byString = by;
        }
    }

    /**
     * Constructs a deadline task with the specified description, deadline, and completion status.
     *
     * @param description the description of the task
     * @param by          the deadline of the task in string format
     * @param isDone      the completion status of the task
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        try {
            // Try to parse the deadline into a LocalDateTime object
            this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            this.byString = by;
        }
        if (isDone) {
            markAsDone();
        }
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return a string representation of the deadline task
     */
    @Override
    public String toString() {
        if (byDateTime != null) {
            // If the deadline is stored in datetime format, display it in a different format
            return "[D]" + super.toString() + " (by: " + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
        } else {
            // If the deadline is stored in string format, display it as it is
            return "[D]" + super.toString() + " (by: " + byString + ")";
        }
    }

    /**
     * Returns a string representation of the deadline task for saving.
     *
     * @return a string representation of the deadline task for saving.
     */
    @Override
    public String toFileString() {
        if (byDateTime != null) {
            // If the deadline is stored in datetime format, save it in a different format
            return "D | " + super.toFileString() + " | " + byDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            // If the deadline is stored in string format, save it as it is
            return "D | " + super.toFileString() + " | " + byString;
        }
    }

}
