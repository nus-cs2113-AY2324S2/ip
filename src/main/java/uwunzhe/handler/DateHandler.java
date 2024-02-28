package uwunzhe.handler;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import uwunzhe.exceptions.UwunzheException;
import uwunzhe.exceptions.ExceptionMessages;

public class DateHandler {
    /**
     * Parses a date string into a LocalDate object.
     * 
     * @param dateString The date string to be parsed.
     * @return The LocalDate object.
     * @throws UwunzheException If the date string is in an invalid format.
     */
    public static LocalDate parseDate(String dateString) throws UwunzheException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new UwunzheException(ExceptionMessages.INVALID_DATE_FORMAT);
        }
    }

    /**
     * Convert a LocalDate object into a string for display.
     * 
     * @param date The LocalDate object to be formatted.
     * @return The formatted string.
     */
    public static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Convert a LocalDate object into a string for storage.
     * 
     * @param date The LocalDate object to be formatted.
     * @return The formatted string.
     */ 
    public static String dateToStorageString(LocalDate date) {
        return date.toString();
    }
}
