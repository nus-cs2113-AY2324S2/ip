package commands;
import Exceptions.ThawException;
import FileManagerPackage.Storage;

import PrintMessages.UI;

/**
 * The Command class provides utility methods for command-related operations.
 */
public class Command {
    UI ui = new UI();

    protected static final int LEN_OF_SLASH_BY = 3;
    protected static final int LEN_OF_SLASH_TO = 3;

    protected static final int LEN_OF_SLASH_FROM = 5;

    protected static final int DATE_TIME_STRING_LEN = 15;

    /**
     * Checks if the given parameter (userInput) is an empty command, meaning just the command word alone.
     *
     * @param usersInput The user's input for the command.
     * @return True if the command is without a description, false otherwise.
     */
    public static boolean commandWithoutDescription(String usersInput) {
        return  usersInput.equals("unmark")   ||
                usersInput.equals("mark")     ||
                usersInput.equals("delete")   ||
                usersInput.equals("todo")     ||
                usersInput.equals("deadline") ||
                usersInput.equals("event")    ||
                usersInput.equals("find");
    }

    /**
     * Checks if the date and time format in the user's input is correct for deadline and event commands.
     *
     * @param userInput The user's input for the command.
     * @return True if the date and time format is correct, false otherwise.
     */
    public static boolean correctDateTimeFormat(String userInput) {
        if (userInput.startsWith("deadline")) {
            String lengthOfDateTimeString = userInput.substring(userInput.indexOf("/by") +
                    LEN_OF_SLASH_BY).strip();
            return lengthOfDateTimeString.length() == 15;
        } else if (userInput.startsWith("event")) {
            String lengthOfFirstDateTime = userInput.substring(userInput.indexOf("/from") +
                    LEN_OF_SLASH_FROM, userInput.indexOf("/to")).strip();

            String lengthOfSecondDateTime = userInput.substring(userInput.indexOf("/to") + LEN_OF_SLASH_TO).strip();

            return lengthOfFirstDateTime.length() == DATE_TIME_STRING_LEN &&
                    lengthOfSecondDateTime.length() == DATE_TIME_STRING_LEN;
        } else {
            return false;
        }
    }
}
