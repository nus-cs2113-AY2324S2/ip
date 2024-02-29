package utilities.parser;

import exceptions.AragornException;
import ui.Constants;

public class ToDoParser {
    private static final String[] splitInput = new String[3];
    protected ToDoParser (String userInput) {
        try {
            splitInput[0] = userInput.substring(4).trim();
            if (splitInput[0].trim().isEmpty()){
                throw new AragornException(Constants.EMPTYDESCRIPTION);
            }
            splitInput[1] = null;
            splitInput[2] = null;
        } catch (AragornException e) {
            System.out.println(e.getMessage());
        }
    }

    protected String[] getSplitInput() {
        return splitInput;
    }
}
