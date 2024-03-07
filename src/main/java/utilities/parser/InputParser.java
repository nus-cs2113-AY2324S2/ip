package utilities.parser;

import ui.Constants;

public class InputParser {
    private String[] splitInput = new String[3];

    /**
     * Parses the input into parts depending on the type of command.
     *
     * @param userInput Input from the user containing the details of the command.
     * @param commandType The type of command inputted.
     */
    public InputParser(String userInput, String commandType) {
        switch (commandType) {
            case Constants.MARK:
                this.splitInput[0] = String.valueOf(Integer.parseInt(userInput.substring(5).trim()) - 1);
                this.splitInput[1] = null;
                this.splitInput[2] = null;
                break;

            case Constants.UNMARK:
            case Constants.DELETE:
                this.splitInput[0] = String.valueOf(Integer.parseInt(userInput.substring(7).trim()) - 1);
                this.splitInput[1] = null;
                this.splitInput[2] = null;
                break;

            case Constants.FIND:
                this.splitInput[0] = userInput.substring(5).trim();
                this.splitInput[1] = null;
                this.splitInput[2] = null;
                break;

            case Constants.TODO:
                this.splitInput = new ToDoParser(userInput).getSplitInput();
                break;

            case Constants.DEADLINE:
                this.splitInput = new DeadlineParser(userInput).getSplitInput();
                break;

            case Constants.EVENT:
                this.splitInput = new EventParser(userInput).getSplitInput();
                break;
        }
    }

    public String[] getSplitInput() {
        return splitInput;
    }

}
