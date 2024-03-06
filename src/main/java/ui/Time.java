package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.format.DateTimeFormatter;

/**
 * The Time class is responsible for standardizing date and time inputs.
 */
public class Time {
    private static final String[] DATE_PATTERNS =  {
        "yyyy-MM-dd",  // 1989-06-04
        "dd-MM-yyyy",  // 04-06-1989
        "yyyy MM dd",  // 1989 06 04
        "dd MM yyyy",  // 04 06 1989
        "yyyy/MM/dd",  // 1989/06/04
        "dd/MM/yyyy",  // 04/06/1989
        "yyyy.MM.dd",  // 1989.06.04
        "dd.MM.yyyy",  // 04.06.1989
        "dd MMM yyyy", // 04 Jun 1989
        "dd-MMM-yyyy", // 04-Jun-1989
        "dd/MMM/yyyy", // 04/Jun/1989
        "MMM dd yyyy", // Jun 04 1989
        "MMM dd, yyyy" // Jun 04, 1989
    };

    private static final String[] TIME_PATTERNS = {
        "HH:mm",    // 24-hour format with leading zeros (e.g. 13:45)
        "hh:mma",   // 12-hour format with leading zeros and AM/PM indicator (e.g. 01:45pm)
        "HHmm",     // 24-hour format without separators (e.g. 1345)
        "hhmma",    // 12-hour format without separators, with AM/PM indicator (e.g. 0145pm)
        "H:mm",     // 24-hour format without leading zeros (e.g. 9:45)
        "h:mma",    // 12-hour format without leading zeros, with AM/PM indicator (e.g. 1:45pm)
        "Hmm",      // 24-hour format without separators and leading zeros (e.g. 945)
        "hmma"      // 12-hour format without separators, leading zeros, with AM/PM indicator (e.g. 145pm)
    };

    /**
     * <h3>Normalize the given date and time input.</h3>
     * The first letter will be capitalized and the rest will be in lower case.
     *
     * @param input The date and time input to be normalized.
     * @return The normalized date and time input.
     */
    private static String ignoreInputCase(String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
    }

    /**
     * Parses the given date and time input.
     *
     * @param input The date and time input to be parsed.
     * @return The parsed date and time input.
     */
    private static Temporal parseDateAndTime(String input) {
        for (String datePattern : DATE_PATTERNS) {
            for (String timePattern : TIME_PATTERNS) {
                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern + " " + timePattern);
                    return LocalDateTime.parse(ignoreInputCase(input), dateTimeFormatter);
                } catch (Exception e) {
                    // Ignore and try the next formatter
                }

                try {
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
                    return LocalDate.parse(ignoreInputCase(input), dateFormatter);
                } catch (Exception e) {
                    // Ignore and try the next formatter
                }

                try {
                    LocalDate today = LocalDate.now();
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERNS[0] + " " + timePattern);
                    return LocalDateTime.parse(today + " "+ input.toLowerCase(), timeFormatter);
                } catch (Exception e) {
                    // Ignore and try the next formatter
                }
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     * <h3>Formats the given date and time input.</h3>
     * 
     * <p>The date and time input will be formatted to MMM dd yyyy, hh:mma<br>
     * (e.g., Oct 15 2019, 6:00pm) with time being optional.</p>
     * 
     * @param dateTime The date and time input to be formatted.
     * @return The formatted date and time input.
     */
    private static String formatDateTime(Temporal dateTime) {
        if (dateTime instanceof LocalDate) {
            return ((LocalDate)dateTime).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return ((LocalDateTime)dateTime).format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mma"));
    }

    /**
     * <h3>To be used on the event or deadline task to standardize the date and time input.</h3>
     * <p>The date and time input will be standardized to MMM dd yyyy, hh:mma<br>
     * (e.g., Oct 15 2019, 6:00pm) with time being optional.</p>
     *
     * @param inputDateTime The date and time input to be standardized.
     * @return The standardized date and time.
     */
    public static String standardize(String inputDateTime) {
        Temporal parsedDateTime = parseDateAndTime(inputDateTime);
        return formatDateTime(parsedDateTime);
    }
}
