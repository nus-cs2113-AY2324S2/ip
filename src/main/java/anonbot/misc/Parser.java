package anonbot.misc;

/**
 * Provides functionality to break up raw user input into commands and respective arguments.
 */
public class Parser {
    public static String getCommand(String userInput) {
        return userInput.split(" ", 2)[0];
    }

    /**
     * Extracts the argument string that comes right after the command, if any.
     *
     * @param userInput The original input by the user, including the command.
     * @return The argument associated with the command. Returns an empty string if only the command is present.
     */
    public static String getCommandArgument(String userInput) {
        try {
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            // If there is no argument associated with that command
            return "";
        }
    }

    /**
     * Gets the task number by converting the string to integer type.
     * Assumes that the input has been checked and is valid.
     *
     * @param taskNumberString The task number that is of type String.
     * @return The task number of type int.
     */
    public static int getTaskNumberFromString(String taskNumberString) {
        return Integer.parseInt(taskNumberString);
    }

    /**
     * Parses the raw deadline description and break into its constituent parts.
     * Does not check if the deadline task has the right format (/by)
     *
     * @param rawDeadlineDescription The unformatted deadline description
     * @return The formatted event description in the form {"Deadline Description", "/by info"}
     */
    public static String[] parseDeadlineDescription(String rawDeadlineDescription) {
        // Format: {"Deadline Description", "/by information"}
        String[] parsedDeadlineSubstrings = {"", ""};

        String[] splitStringArray = rawDeadlineDescription.split(" /by ", 2);
        parsedDeadlineSubstrings[0] = splitStringArray[0];

        if (splitStringArray.length == 2) {
            parsedDeadlineSubstrings[1] = splitStringArray[1];
        }

        return parsedDeadlineSubstrings;
    }

    /**
     * Parses the raw event description and break into its constituent parts.
     * Does not check if the event task has the right format (/from, /to).
     *
     * @param rawEventDescription The unformatted event description.
     * @return The formatted event description in the form {"Event Description", "/from info", "/to info"}
     */
    public static String[] parseEventDescription(String rawEventDescription) {
        // Format: {"Deadline Description", "/from information", "/to information"}
        String[] parsedEventSubstrings = {"", "", ""};

        String[] splitStringArray = rawEventDescription.split(" /from ", 2);
        parsedEventSubstrings[0] = splitStringArray[0];

        if (splitStringArray.length != 2) {
            return parsedEventSubstrings;
        }

        String[] fromToArray = splitStringArray[1].split(" /to ", 2);
        parsedEventSubstrings[1] = fromToArray[0];
        if (fromToArray.length == 2) {
            parsedEventSubstrings[2] = fromToArray[1];
        }
        return parsedEventSubstrings;
    }
}
