package jeff.tasks;

import jeff.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 * Extends the Task class and adds functionality specific to deadline tasks,
 * such as storing the deadline date and formatting the task description.
 */
public class Deadline extends Task {
    private static final String PATTERN = "MMM d yyyy";
    protected LocalDate by;

    /**
     * Constructs a Deadline object with the given description and deadline date.
     *
     * @param description Description of the deadline task.
     * @param by Deadline date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return String representing the deadline task.
     */
    @Override
    public String toString() {
        String byFormatted = by.format(DateTimeFormatter.ofPattern(PATTERN));
        return "[D]" + super.toString() + "(by: " + byFormatted + ")";
    }

    /**
     * Returns a string representation of the deadline task suitable for storing in a file.
     *
     * @return String representing the deadline task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + by;
    }
}
