package Alexis.console;

import Alexis.exception.MissingFieldException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Parser {

    public static Command parseCommand(String input) {
        String firstWord = getFirstWord(input);
        try {
            return Command.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            Ui.printCommandErrorMessage();
            return null;
        }
    }

    public static String getFirstWord(String input) {
        int firstSpace = input.indexOf(" ");
        if (firstSpace == -1) {
            return input;
        } else {
            return input.substring(0, firstSpace).trim();
        }
    }

    public static String parseDescription(String input) throws MissingFieldException {
        int firstSpace = input.indexOf(" ");
        if (firstSpace == -1) {
            throw new MissingFieldException();
        }
        return input.substring(firstSpace + 1).trim();
    }

    public static String getDateTime(String input) {
        String dateInput = "yyyy-MM-dd HHmm";
        String dateOutput = "MMM dd yyyy, K.mm a";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(dateInput);

        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(dateOutput);
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return input;
        }
    }
}
