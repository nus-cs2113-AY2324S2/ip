package Quokka.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline object with the specified description, deadline, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline of the task.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Constructs a Deadline object with the specified description, deadline string, and completion status.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline string of the task.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
