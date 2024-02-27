package junbot.parser;

import junbot.error.InvalidInputException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Parser {


    public int convertToArrayIndex(String listNumberAsString) {
        int listNumberAsInt = Integer.parseInt(listNumberAsString);
        int arrayIndex = listNumberAsInt - 1;
        return arrayIndex;
    }

    public String removeCommandIndicator(String command, String commandIndicator) {
        String userInput = command.replace(commandIndicator, "").trim();
        return userInput;
    }

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


