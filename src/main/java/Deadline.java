import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the Phoebe application.
 * A deadline task is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by; // The deadline date/time

    /**
     * Constructs a Deadline task with the specified description and deadline.
     * The deadline is parsed from a String to a LocalDateTime object using a predefined format.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time as a String in the format "yyyy-MM-dd HHmm".
     * @throws DateTimeParseException If the by parameter does not conform to the expected format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.type = "D"; // Type identifier for deadlines
    }

    /**
     * Returns the string representation of the deadline task, including its type, status,
     * description, and deadline date/time in a human-readable format.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    /**
     * Converts the deadline task into a format suitable for file storage.
     * The format includes the task type, completion status, description, and deadline date/time.
     *
     * @return A string representation of the deadline task in a file storage format.
     */
    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description, this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
