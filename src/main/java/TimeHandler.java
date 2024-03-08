import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class TimeHandler {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("d/M/yyyy[ HHmm]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter(Locale.ENGLISH);

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter DATE_TIME_SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DATE_SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * transfer String to localDayTime
     * @param input The string need to be transferred
     * @return the LocalDayTime transferred
     */
    public static LocalDateTime parseDateTime(String input) {
        return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
    }

    /**
     * transfer LocalDateTime to String in format for printing purpose
     * @param dateTime The LocalDateTime needed to be transferred
     * @return the String of dateTime to save
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            return dateTime.format(DATE_FORMAT); // Date only
        } else {
            return dateTime.format(DATE_TIME_FORMAT); // Date and time
        }
    }

    /**
     * transfer LocalDateTime to String in format for saving purpose
     * @param dateTime The LocalDateTime needed to transferred
     * @return the String of dateTime to save
     */
    public static String formatDateTimeSave(LocalDateTime dateTime) {
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            return dateTime.format(DATE_SAVE_FORMAT); // Date only
        } else {
            return dateTime.format(DATE_TIME_SAVE_FORMAT); // Date and time
        }
    }
}

