package yuki;

import static yuki.Constants.INDEX_COMMAND;
import static yuki.Constants.INDEX_DESCRIPTION;
import static yuki.Constants.regexMatcher;

/**
 * Methods to parse input by the user.
 */
public class InputParser {

    /**
     * Returns command entered by the user, expected to be at beginning of string.
     * Error handling in TaskList.java
     *
     * @param userInput input from the user in the command line.
     * @return the command keyword. e.g. list, mark, unmark.
     */
    public static String parseCommand(String userInput) {
        return userInput.trim().split(" ")[INDEX_COMMAND];
    }

    /**
     * Returns description entered by the user, expected to be after the command.
     * Error handling in TaskList.java
     *
     * @param userInput input from the user in the command line.
     * @return the part of the user input after the command. e.g. homework /by 9pm
     */
    public static String parseDescription(String userInput) {
        return userInput.trim().split(" ")[INDEX_DESCRIPTION];
    }

    /**
     * Returns details out input entered by the user, splitted into description, and any dates.
     * Error handling in TaskList.java
     *
     * @param userInput input from the user in the command line.
     */
    public static String[] parseInput(String userInput){
        return userInput.trim().split(regexMatcher);
    }
}
