package Alexis.console;

import Alexis.exception.MissingFieldException;

public enum Command {
    LIST,
    BYE,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    SAVE;


    public static Command getCommand(String type) {
        try {
            return Command.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            Console.printCommandErrorMessage();
            return null;
        }
    }

    public static Command getFirstWord(String input) {
        int firstSpace = input.indexOf(" ");
        String firstWord;
        // Check if there is no space in the input string
        if (firstSpace == -1) {
            // If no space is found, the entire input string is considered the first word
            firstWord = input;
        } else {
            // If a space is found, extract the substring up to the first space
            firstWord = input.substring(0, firstSpace);
        }
        return Command.getCommand(firstWord);
    }


    public static String getDescription(String input) throws MissingFieldException {
        int firstSpace = input.indexOf(" ");
        // Check if there is no space in the input string
        if (firstSpace == -1) {
            throw new MissingFieldException(); // Return an empty string if no description is present
        }
        // Otherwise, return the substring after the first space
        return input.substring(firstSpace + 1);
    }

}
