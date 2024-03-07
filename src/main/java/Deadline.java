/**
 * The Deadline class represents a task with a deadline.
 * It extends the Task class and adds a deadline date and time.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new Deadline object with the given description and deadline.
     * @param description The task description
     * @param by The deadline date and time in the format "yyyy-MM-dd HHmm"
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Gets the deadline date and time.
     * @return The deadline date and time
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * @return The string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + description + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task suitable for writing to a file.
     * @return The string representation
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}