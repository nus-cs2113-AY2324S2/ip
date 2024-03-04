package humi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Parse elements of the input command
 */
public class Parser {
    public static String todoDescription;
    public static String deadlineDescription;
    public static String deadlineDate;
    public static String eventDescription;
    public static String eventStartDate;
    public static String eventEndDate;
    public static int taskIndex;

    /**
     * Determines the type of the command and passes it to the corresponding parser.
     * @param command Input received from the user
     * @throws HumiException If invalid command is received
     */
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

    /**
     * Parse todo task and set the todoDescription to the corresponding task description.
     * @param command Todo command input received from the user
     * @throws HumiException If the task description is empty
     */
    private static void parseTodo(String command) throws HumiException {
        String trimmedCommand = command.trim();
        if (trimmedCommand.length() > 4) {
            todoDescription = trimmedCommand.substring(5);
        } else {
            throw new HumiException("Description of a todo cannot be empty.");
        }
    }

    /**
     * Parse deadline task and set the deadlineDescription and deadlineDate
     * to the corresponding task description and deadline date.
     * @param command Deadline command input received from the user
     * @throws HumiException If there is no description or deadline date
     */
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

    /**
     * Parse event task and set the eventDescription, eventStartDate, eventEndDate
     * to the corresponding task description, starting date, and ending date.
     * @param command Event command input received from the user
     * @throws HumiException If there is no description, start date, or end date
     */
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

    /**
     * Convert each command to text format that will be saved in the data folder
     * @param command Input command received from the user
     * @return Command in text format
     */
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

    /**
     * Determines whether the given input string is a valid date.
     * @param input Input string to be checked
     * @return true if the given string is a valid date, false otherwise
     */
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

    /**
     * Formats the input date to MMM d yyyy (e.g. Oct 15 2019)
     * @param input Input string to be formatted
     * @return date in the format of MMM d yyyy if the given input is a valid date,
     * returns the initial string otherwise.
     */
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

    /**
     * Determines whether the given input string is a valid time.
     * @param input Input string to be checked
     * @return true if the given string is a valid time, false otherwise
     */
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

    /**
     * Formats the input time to hh:mm AM/PM format
     * @param input Input string to be formatted
     * @return time in the format of hh:mm if the given input is a valid time,
     * returns the initial string otherwise.
     */
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

    /**
     * Parse both date and time from the given input
     * @param input Input string to be formatted
     * @return Formatted string in the form of MMM d yyyy hh:mm AM/PM
     */
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
