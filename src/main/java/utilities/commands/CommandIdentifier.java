package utilities.commands;

import ui.Constants;

public class CommandIdentifier {
    public static String commandIdentifier(String userInput) {
        String commandType;

        if (userInput.trim().equals(Constants.BYE.toLowerCase())) {
            commandType = Constants.BYE;
        } else if (userInput.trim().equals(Constants.LIST.toLowerCase())) {
            commandType = Constants.LIST;
        } else if (userInput.trim().startsWith(Constants.UNMARK.toLowerCase())) {
            commandType = Constants.UNMARK;
        } else if (userInput.trim().startsWith(Constants.MARK.toLowerCase())) {
            commandType = Constants.MARK;
        } else if (userInput.trim().startsWith(Constants.DELETE.toLowerCase())) {
            commandType = Constants.DELETE;
        } else if (userInput.trim().startsWith(Constants.TODO.toLowerCase())) {
            commandType = Constants.TODO;
        } else if (userInput.trim().startsWith(Constants.DEADLINE.toLowerCase())) {
            commandType = Constants.DEADLINE;
        } else if (userInput.trim().startsWith(Constants.EVENT.toLowerCase())) {
            commandType = Constants.EVENT;
        } else if (userInput.trim().equals(Constants.HELP.toLowerCase())) {
            commandType = Constants.HELP;
        } else if (userInput.trim().startsWith(Constants.FIND.toLowerCase())) {
            commandType = Constants.FIND;
        } else {
            commandType = Constants.INVALID;
        }
        return commandType;
    }
}
