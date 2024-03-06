package ChelleCommands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description) {
        super(parseDescription(description));
        this.by = parseBy(description);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    private static String parseDescription(String description) {
        String[] descriptionPart = description.split("/by", 2);
        return descriptionPart[0].trim();
    }

    private String parseBy(String description) {
        String[] byPart = description.split("/by", 2);
        LocalDateTime dateTime = parseDateTime(byPart[1].trim());

        if (dateTime != null) {
            return formatDateTime(dateTime);
        } else {
            return byPart[1].trim();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * format dateTime to the form (12 Apr 2024 16:00hrs)
     * if time is not indicated it will take the form (12 Apr 2024)
     *
     * @param dateTime parsed time and date
     * @return string with formatted date and/or time
     */
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy[ HH:mm'hrs']");
        return dateTime.getHour() == 0 && dateTime.getMinute() == 0
                ? dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                : dateTime.format(formatter);
    }

    /**
     * processes input to identify the day, month, year and time
     *
     * @param originalInput the user's input
     * @return formatted date and time
     */
    private LocalDateTime parseDateTime(String originalInput) {
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("[d][d]/[M][M]/yyyy[ [HHmm]]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .toFormatter(Locale.ENGLISH);

            return LocalDateTime.parse(originalInput, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
