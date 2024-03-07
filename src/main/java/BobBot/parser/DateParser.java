package BobBot.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {

    public static LocalDate parseDateFromDateString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }

    public static String detectDateFromByString(String byString) {
        final int DATE_LENGTH = 10;
        int startingIndex = byString.indexOf("-") - "yyyy".length();
        int endingIndex = startingIndex + DATE_LENGTH;
        return byString.substring(startingIndex, endingIndex);
    }

    public static boolean containsValidDateString(String byString) {
        String dateString = "";

        if (byString.contains("-") && byString.length() >= 10) {
            dateString = detectDateFromByString(byString);
        }

        String regexString = ".*\\d{4}-\\d{2}-\\d{2}.*";
        return dateString.matches(regexString);
    }
}
