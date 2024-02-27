package junbot.parser;

import junbot.error.InvalidInputException;

import java.io.IOException;

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

}


