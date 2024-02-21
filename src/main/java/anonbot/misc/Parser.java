package anonbot.misc;

public class Parser {
    public static String getCommand(String userInput) {
        return userInput.split(" ", 2)[0];
    }

    public static String getCommandArgument(String userInput) {
        try {
            return userInput.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            // If there is no argument associated with that command
            return "";
        }
    }

    /**
     * Checks if the command argument contains a purely numeric string.
     *
     * @param commandArgument The argument portion of the user input.
     * @return Whether the argument is a number of type String.
     */
    public static boolean isValidTaskNumberString(String commandArgument) {
        // We are only expecting 1 argument since this is only called by the `mark` and `unmark` commands
        String[] argumentList = commandArgument.split(" ");
        if (argumentList.length != 1) {
            return false;
        }

        try {
            Integer.parseInt(commandArgument);
        } catch (NumberFormatException e) {
            // We reject those with a mix of alphanumeric characters
            return false;
        }
        return true;
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
     * Parse the raw deadline description and break into its constituent parts.
     *
     * @param rawDeadlineDescription The unformatted deadline description
     * @return The formatted event description in the form {"Deadline Description", "/by info"}
     */
    public static String[] parseDeadlineDescription(String rawDeadlineDescription) {
        // Format: {"Deadline Description", "/by information"}
        String[] parsedDeadlineSubstrings = {"", ""};

        String[] splitStringArray = rawDeadlineDescription.split(" /by ", 2);
        parsedDeadlineSubstrings[0] = splitStringArray[0];

        // We currently do not check if the deadline task has the right format (/by)
        if (splitStringArray.length == 2) {
            parsedDeadlineSubstrings[1] = splitStringArray[1];
        }

        return parsedDeadlineSubstrings;
    }

    /**
     * Parse the raw event description and break into its constituent parts.
     *
     * @param rawEventDescription The unformatted event description.
     * @return The formatted event description in the form {"Event Description", "/from info", "/to info"}
     */
    public static String[] parseEventDescription(String rawEventDescription) {
        // Format: {"Deadline Description", "/from information", "/to information"}
        String[] parsedEventSubstrings = {"", "", ""};

        String[] splitStringArray = rawEventDescription.split(" /from ", 2);
        parsedEventSubstrings[0] = splitStringArray[0];

        // We currently do not check if the event task has the right format (/from, /to)
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
