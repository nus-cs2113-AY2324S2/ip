package utilities.file;

import exceptions.AragornException;
import ui.Constants;

public class DeadlineParser {

    private static final String[] splitInput = new String[3];

    protected DeadlineParser (String userInput) {
        String[] splitDeadline;
        try {
            splitDeadline = userInput.split(Constants.BYREGEX, 2);
            splitInput[0] = splitDeadline[0].substring(8).trim();
            if (splitInput[0].trim().isEmpty()) {
                throw new AragornException(Constants.EMPTYDESCRIPTION);
            }
            splitInput[1] = splitDeadline[1].trim();
            if (splitInput[1].trim().isEmpty()) {
                throw new AragornException(Constants.EMPTYDEADLINE);
            }
            splitInput[2] = null;
        } catch (AragornException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Constants.INVALIDFORMAT);
        }
    }

    protected String[] getSplitInput() {
        return splitInput;
    }
}
