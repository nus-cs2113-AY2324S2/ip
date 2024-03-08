package BobBot.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Implements a date parser that interprets date strings and performs the 
 * necessary operations.
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public class DateParser {

    /*
     * Converts date string to LocalDate format.
     * 
     * @param dateString The date string to parse.
     * @return The date parsed from the date string.
     */
    public static LocalDate parseDateFromDateString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }

    /*
     * Detects the date from a date string in the format by "yyyy-MM-dd".
     */
    public static String detectDateFromByString(String byString) {
        final int DATE_LENGTH = 10;
        int startingIndex = byString.indexOf("-") - "yyyy".length();
        int endingIndex = startingIndex + DATE_LENGTH;
        return byString.substring(startingIndex, endingIndex);
    }

    /*
     * Checks in the string contains a valid date string in the format 
     * "yyyy-MM-dd".
     * 
     * @param byString The date string to check.
     * @return True if the date string is in the specified format, false otherwise.
     */
    public static boolean containsValidDateString(String byString) {
        String dateString = "";

        if (byString.contains("-") && byString.length() >= 10) {
            dateString = detectDateFromByString(byString);
        }

        String regexString = ".*\\d{4}-\\d{2}-\\d{2}.*";
        return dateString.matches(regexString);
    }
}
