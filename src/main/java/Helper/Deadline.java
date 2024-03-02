package Helper;

/**
 * The Deadline class represents a task with a deadline.
 * It inherits from the Task class and adds functionality specific to Deadline tasks.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;


public class Deadline extends Task {
    private LocalDateTime byDateTime;

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
     * Returns a string representation of the Deadline object.
     * The representation includes the task type identifier, description, and deadline.
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
     * Returns a string representation of the Deadline object in a format suitable for file storage.
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
