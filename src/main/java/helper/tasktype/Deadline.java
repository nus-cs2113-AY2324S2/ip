package helper.tasktype;

import helper.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

/**
 * The Deadline class represents a task with a deadline.
 * It inherits from the Task class and adds functionality specific to Deadline tasks.
 */
public class Deadline extends Task {
    private final LocalDateTime byDateTime;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description Description of the task.
     * @param by The deadline time for the task.
     */

    public Deadline(String description, String by) {
        super(description);
        this.byDateTime = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) {
        return LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * {@inheritDoc}
     *
     * Overrides the parent method to include a custom date format for Deadline tasks and an identifier.
     *
     * @return A string representation of the Deadline object.
     */

    @Override
    public String toString() {
        DateTimeFormatter customFormatter = new DateTimeFormatterBuilder()
                .appendPattern("MMM dd yyyy")
                .toFormatter(Locale.ENGLISH);

        String customFormatByDate = byDateTime.toLocalDate().format(customFormatter);
        return "[D]" + super.toString() +
                " (by: " + customFormatByDate + " " + byDateTime.toLocalTime() + ")";
    }

    /**
     * {@inheritDoc}
     *
     * Overrides the parent method to include a custom file representation for Deadline tasks.
     * The representation includes the task type identifier, status, description, and deadline.
     *
     * @return A string representation of the Deadline object for file storage.
     */

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                byDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

}
