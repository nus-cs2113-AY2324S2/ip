package Alexis.console;

import Alexis.exception.MissingFieldException;

/**
 * The Parser class is responsible for parsing user inputs and extracting relevant information
 * for further processing by the application.
 */
public class Parser {

    /**
     * Parses the first word of the user's input and returns the command to be executed.
     *
     * @param input The input entered by the user.
     * @return The Command corresponding to the first word of the input or null if command does not exist.
     */
    public static Command parseCommand(String input) {
        String firstWord = getFirstWord(input);
        try {
            return Command.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            Ui.printCommandErrorMessage();
            return null;
        }
    }

    /**
     * Extracts and returns the first word from the user's input.
     * If the input contains no spaces, the whole input string is taken as the first word.
     *
     * @param input The input entered by the user.
     * @return The first word of the input as a String.
     */
    private static String getFirstWord(String input) {
        int firstSpace = input.indexOf(" ");
        if (firstSpace == -1) {
            return input;
        } else {
            return input.substring(0, firstSpace).trim();
        }
    }

    /**
     * Parses the input to extract the description of a task and returns the description as a string.
     *
     * @param input The input entered by the user.
     * @return The description part of the input.
     * @throws MissingFieldException If the description is missing.
     */
    public static String parseDescription(String input) throws MissingFieldException {
        int firstSpace = input.indexOf(" ");
        if (firstSpace == -1) {
            throw new MissingFieldException();
        }
        return input.substring(firstSpace + 1).trim();
    }
}
