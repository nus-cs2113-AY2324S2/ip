package Helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

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

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                fromDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " | " +
                toDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
