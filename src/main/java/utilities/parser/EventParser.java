package utilities.parser;

import exceptions.AragornException;
import ui.Constants;

public class EventParser {

    private static final String[] splitInput = new String[3];
    protected EventParser (String userInput) {
        String[] splitEvent;
        String[] splitDeadline;
        try {
            splitDeadline = userInput.split(Constants.FROMREGEX, 2);

            splitInput[0] = splitDeadline[0].substring(5).trim();
            if (splitInput[0].trim().isEmpty()) {
                throw new AragornException(Constants.EMPTYDESCRIPTION);
            }
            splitEvent = splitDeadline[1].split(Constants.TOREGEX, 2);
            splitInput[1] = splitEvent[0].trim();
            if (splitInput[1].trim().isEmpty()) {
                throw new AragornException(Constants.EMPTYEVENTSTART);
            }
            splitInput[2] = splitEvent[1].trim();
            if (splitInput[2].trim().isEmpty()) {
                throw new AragornException(Constants.EMPTYEVENTEND);
            }
        } catch (AragornException e) {
            System.out.println(e.getMessage());
        }  catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Constants.INVALIDFORMAT);
        } catch (NullPointerException e) {
            return;
        }
    }

    protected String[] getSplitInput() {
        return splitInput;
    }
}
