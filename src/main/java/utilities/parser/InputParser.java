package utilities.file;

import exceptions.AragornException;
import ui.Constants;

public class InputParser {
    private String[] splitInput = new String[3];

    public InputParser(String userInput, String commandType) {
        switch (commandType) {
            case Constants.MARK:
                this.splitInput[0] = String.valueOf(Integer.parseInt(userInput.substring(5).trim()) - 1);
                this.splitInput[1] = null;
                this.splitInput[2] = null;
                break;

            case Constants.UNMARK:
                this.splitInput[0] = String.valueOf(Integer.parseInt(userInput.substring(7).trim()) - 1);
                this.splitInput[1] = null;
                this.splitInput[2] = null;
                break;

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
