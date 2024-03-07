package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Time class is responsible for standardizing date and time inputs.
 */
public class Time {
    private static final String[] DATE_PATTERNS = {
            "yyyy[-][ ][.][/]MM[-][ ][.][/]dd",  // 1989<?>06<?>04
            "dd[-][ ][.][/]MM[-][ ][.][/]yyyy",  // 04<?>06<?>1989
            "dd[-][ ][.][/]MMM[-][ ][.][/]yyyy", // 04<?>Jun<?>1989
            "MMM dd[,] yyyy", // Jun 04(,) 1989
    };

    private static final String[] TIME_PATTERNS = {
            "HH[:]mm",    // 24-hour format with leading zeros (e.g. 13:45, 1345)
            "hh[:]mma",   // 12-hour format with leading zeros and AM/PM indicator (e.g. 01:45pm, 0145pm)
            "H:mm",     // 24-hour format without leading zeros (e.g. 9:45)
            "h:mma",    // 12-hour format without leading zeros, with AM/PM indicator (e.g. 1:45pm)
            "Hmm",      // 24-hour format without separators and leading zeros (e.g. 945)
            "hmma"      // 12-hour format without separators, leading zeros, with AM/PM indicator (e.g. 145pm)
    };

    /**
     * Parses the given date and time input.
     *
     * @param input The date and time input to be parsed.
     * @throws IllegalArgumentException If the input does not match any of the date and time patterns.
     * @return The parsed date and time input.
     */
    public static LocalDateTime parseDateAndTime(String input) throws IllegalArgumentException {
        for (String datePattern : DATE_PATTERNS) {
            for (String timePattern : TIME_PATTERNS) {
                try {
                    DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .appendPattern(datePattern).appendPattern(" ").appendPattern(timePattern)
                            .toFormatter();

                    return LocalDateTime.parse(input, dateTimeFormatter);
                } catch (Exception e) {
                    // Ignore and try the next formatter
                }

                try {
                    DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .appendPattern(datePattern)
                            .toFormatter();

                    return LocalDate.parse(input, dateFormatter).atStartOfDay();
                } catch (Exception e) {
                    // Ignore and try the next formatter
                }

                try {
                    String today = LocalDate.now().toString();
                    DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .append(DateTimeFormatter.ISO_LOCAL_DATE)
                            .appendPattern(" ").appendPattern(timePattern)
                            .toFormatter();

                    return LocalDateTime.parse(today + " " + input.toLowerCase(), timeFormatter);
                } catch (Exception e) {
                    // Ignore and try the next formatter
                }
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     * <h3>To be used on the event or deadline task to standardize the date and time input.</h3>
     * <p>The date and time input will be standardized to MMM dd, yyyy, h:mma<br>
     * (e.g., Oct 15, 2019, 6:00PM) with time being optional.</p>
     *
     * @param inputDateTime The date and time input to be standardized.
     * @throws IllegalArgumentException If the input date and time is invalid.
     * @return The standardized date and time.
     */
    public static String standardize(String inputDateTime) throws IllegalArgumentException {
        LocalDateTime parsedDateTime = parseDateAndTime(inputDateTime);

        return parsedDateTime
                .format(DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .withLocale(Locale.US));
    }
}
