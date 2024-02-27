package junbot.parser;

import junbot.error.InvalidInputException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Parser {


    /**
     * Converts a list number (as string) to an array index. A task with a displayed list number n would have
     * a corresponding array index of n-1
     *
     * @param listNumberAsString The list number as a string.
     * @return The corresponding array index.
     */
    public int convertToArrayIndex(String listNumberAsString) {
        int listNumberAsInt = Integer.parseInt(listNumberAsString);
        int arrayIndex = listNumberAsInt - 1;
        return arrayIndex;
    }

    /**
     * Removes the command indicator from the user input and returns the remaining user input
     *
     * @param command The full command input by the user.
     * @param commandIndicator The indicator of the command (e.g., "mark", "unmark").
     * @return The user input without the command indicator.
     */
    public String removeCommandIndicator(String command, String commandIndicator) {
        String userInput = command.replace(commandIndicator, "").trim();
        return userInput;
    }

    /**
     * Extracts the command indicator from the full user command.
     *
     * @param command The full command input by the user.
     * @return The command indicator (e.g., "mark", "unmark").
     */
    public String getCommandIndicator(String command) {
        int i = command.indexOf(' ');
        String commandIndicator;
        if (i != -1) {
            commandIndicator = command.substring(0, i);
        } else {
            commandIndicator = command;
        }
        return commandIndicator;
    }

    public LocalDate parseDate(String date) throws InvalidInputException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new InvalidInputException("Invalid date/time format. Please provide the date in the format 'd/M/yyyy HHmm'.");
        }
    }

}


