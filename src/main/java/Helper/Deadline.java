package Helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime byDateTime;

    public Deadline(String description, String by) {
        super(description);
        this.byDateTime = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) {
        return LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter customFormatter = new DateTimeFormatterBuilder()
                .appendPattern("MMM dd yyyy")
                .toFormatter(Locale.ENGLISH);

        String customFormatByDate = byDateTime.toLocalDate().format(customFormatter);
        return "[D]" + super.toString() +
                " (by: " + customFormatByDate + " " + byDateTime.toLocalTime() + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                byDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

}
