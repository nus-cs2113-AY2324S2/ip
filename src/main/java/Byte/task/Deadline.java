package Byte.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a deadline task with the specified description, deadline, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline of the task.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of the deadline task in the format for saving to a file.
     *
     * @return A string representation of the deadline task for saving to a file.
     */
    public String toFileString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
