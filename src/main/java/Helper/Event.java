package Helper;

/**
 * The Event class represents a task that occurs within a specific time frame.
 * It inherits from the Task class and adds functionality specific to Event tasks.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;


public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Constructs an Event object with the given description and time frame.
     *
     * @param description Description of the task.
     * @param from The starting time of the Event.
     * @param to The ending time of the Event.
     */

    public Event(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns a string representation of the Event object.
     * The representation includes the task type identifier, description, and timeframe.
     *
     * @return A string representation of the Event object.
     */

    @Override
    public String toString() {
        DateTimeFormatter customFormatter = new DateTimeFormatterBuilder()
                .appendPattern("MMM dd yyyy")
                .toFormatter(Locale.ENGLISH);

        String customFormatFromDate = fromDateTime.toLocalDate().format(customFormatter);

        String customFormatToDate = toDateTime.toLocalDate().format(customFormatter);

        return "[E]" + super.toString() +
                " (from: " + customFormatFromDate + " " + fromDateTime.toLocalTime() +
                " to: " + customFormatToDate + " " + toDateTime.toLocalTime() + ")";
    }

    /**
     * Returns a string representation of the Event object in a format suitable for file storage.
     * The representation includes the task type identifier, status, description, and timeframe.
     *
     * @return A string representation of the Event object for file storage.
     */

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                fromDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " | " +
                toDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
