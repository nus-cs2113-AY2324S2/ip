package humi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Parser {
    public static boolean isValidDate(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(input.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static String parseDate(String input) {
        if (input.length() < 10) {
            return input;
        }
        String date = input.substring(0, 10);
        if (isValidDate(date)) {
            LocalDate d = LocalDate.parse(date);
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return date;
    }

    public static boolean isValidTime(String input) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(input.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static String parseTime(String input) {
        if (input.length() < 5) {
            return input;
        }
        String time = (isValidDate(input) && input.length() > 11)? input.substring(11) : input.substring(0, 5);
        if (isValidTime(time)) {
            // Parsing hours, minutes and seconds in array
            String[] arr = time.split(":");

            // Converting hours into integer
            int hh = Integer.parseInt(arr[0]);
            String format;

            if (hh > 12) {
                hh -= 12;
                format = "PM";
            }
            else if (hh == 00) {
                hh = 12;
                format = "AM";
            }
            else if (hh == 12) {
                hh = 12;
                format = "PM";
            }
            else {
                format = "AM";
            }

            // Converting hh to String and padding it with 0 on left side
            String hour = String.format("%02d", hh);
            String minute = arr[1];

            time = hour + ":" + minute + " " + format;
            return time;
        }
        return time;
    }

    public static String parseDateAndTime(String input) {
        if (input.length() < 5) {
            return input;
        }
        String time = (isValidDate(input) && input.length() > 11)? input.substring(11) : input.substring(0, 5);
        if (isValidDate(input) && isValidTime(time)) {
            return parseDate(input) + " " + parseTime(input);
        }
        else if (isValidDate(input)) {
            return parseDate(input);
        }
        else if (isValidTime(input)) {
            return parseTime(input);
        }
        return input;
    }
}
