package Templates;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import Exceptions.MarioDateTimePassed;

public class BaseDate implements Serializable {
    private static final List<String> dateFormats = List.of(
            "yyyy-MM-dd",
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "dd:MM:yyyy",
            "ddMMyyyy",
            "ddMMyy");

    private static final List<String> timeFormats = List.of(
            "HH:mm",
            "HHmm");

    private static final ArrayList<String> dateTimeFormats = dateTimeVary();
    public static DateTimeFormatter formatter = null;
    LocalDateTime dateTime = null;

    public BaseDate(String args) throws Exception {
        args = args.strip();
        if (!args.contains(" ")) {
            args = args + " 0000";
        }
        for (String format : dateTimeFormats) {
            try {
                formatter = DateTimeFormatter.ofPattern(format);
                dateTime = LocalDateTime.parse(args, formatter);
                if (dateTime.isBefore(LocalDateTime.now())) {
                    throw new MarioDateTimePassed();
                }
                break;
            } catch (DateTimeParseException e) {
                continue;
            }

        }
        if (dateTime == null) {
            throw new Exception(
                    String.format(
                            "Please input date in one of the correct formats: %s\n\n(Optional) Please input time in one of the correct formats: %s\n",
                            dateFormats, timeFormats));
        }
    }

    private static ArrayList<String> dateTimeVary() {
        ArrayList<String> varyList = new ArrayList<String>();
        for (String dateFormat : dateFormats) {
            for (String timeFormat : timeFormats) {
                varyList.add(String.format("%s %s", dateFormat, timeFormat));
                varyList.add(String.format("%s %s", timeFormat, dateFormat));
            }
        }
        return varyList;
    }

    @Override
    public String toString() {
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        return String.format("%s", dateTime.format(formatter));
    }

    public boolean equals(BaseDate otherDate) {
        if (otherDate != null) {
            return this.dateTime.toLocalDate().equals(otherDate.dateTime.toLocalDate());
        }
        return false;
    }

    public boolean isBefore(BaseDate otherDate) {
        if (otherDate != null) {
            return this.dateTime.isBefore(otherDate.dateTime);
        }
        return false;
    }
}
