package humi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parser {
    public static String todoDescription;
    public static String deadlineDescription;
    public static String deadlineDate;
    public static String eventDescription;
    public static String eventStartDate;
    public static String eventEndDate;
    public static int taskIndex;

    public static void parseCommand(String command) throws HumiException {
        if (command.startsWith("todo")) {
            parseTodo(command);
        } else if (command.startsWith("deadline")) {
            parseDeadline(command);
        } else if (command.startsWith("event")) {
            parseEvent(command);
        } else if (command.startsWith("mark")) {
            taskIndex = Integer.parseInt(command.substring(5));
        } else if (command.startsWith("unmark")) {
            taskIndex = Integer.parseInt(command.substring(7));
        } else if (command.startsWith("delete")) {
            taskIndex = Integer.parseInt(command.substring(7));
        } else {
            throw new HumiException("Failed parsing command.");
        }
    }

    private static void parseTodo(String command) throws HumiException {
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 4) {
            todoDescription = trimmedCommand.substring(5);
        } else {
            throw new HumiException("Description of a todo cannot be empty.");
        }
    }

    private static void parseDeadline(String command) throws HumiException {
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 8) {
            String[] splitArray = trimmedCommand.split("/");
            if (splitArray.length >= 2) {
                deadlineDescription = splitArray[0].substring(9).trim();
                deadlineDate = splitArray[1].substring(3).trim();
                deadlineDate = parseDateAndTime(deadlineDate);
            } else {
                throw new HumiException("Please specify the deadline date.");
            }
        } else {
            throw new HumiException("Description of a deadline cannot be empty.");
        }
    }

    private static void parseEvent(String command) throws HumiException {
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 5) {
            String[] splitArray = trimmedCommand.split("/");
            if (splitArray.length >= 3) {
                eventDescription = splitArray[0].substring(6).trim();
                eventStartDate = splitArray[1].substring(5).trim();
                eventStartDate = Parser.parseDateAndTime(eventStartDate);
                eventEndDate = splitArray[2].substring(3).trim();
                eventEndDate = Parser.parseDateAndTime(eventEndDate);
            } else {
                throw new HumiException("Please specify both the start date and end date");
            }
        } else {
            throw new HumiException("Description of an event cannot be empty.");
        }
    }

    public static String convertCommandToText (String command) {
        String trimmedCommand = command.trim();
        String text = "";
        if (command.startsWith("todo")) {
            String description = trimmedCommand.substring(5);
            text = "T/0/" + description;
        } else if (command.startsWith("deadline")) {
            String[] splitArray = trimmedCommand.split("/");
            String description = splitArray[0].substring(9);
            String deadline = splitArray[1].substring(3);
            text = "D/0/" + description + "/" + deadline;
        } else if (command.startsWith("event")) {
            String[] splitArray = trimmedCommand.split("/");
            String description = splitArray[0].substring(6);
            String startDate = splitArray[1].substring(5);
            String endDate = splitArray[2].substring(3);
            text = "E/0/" + description + "/" + startDate + "/" + endDate;
        }
        return text;
    }

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
