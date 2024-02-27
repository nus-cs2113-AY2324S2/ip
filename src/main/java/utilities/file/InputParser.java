package utilities.file;

import exceptions.AragornException;
import ui.Constants;

public class InputParser {
    private final String[] splitInput = new String[3];

    public InputParser(String userInput, String commandType) {
        String[] splitDeadline;
        String[] splitEvent;
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
                try {
                    this.splitInput[0] = userInput.substring(4).trim();
                    if (this.splitInput[0].trim().isEmpty()){
                        throw new AragornException(Constants.EMPTYDESCRIPTION);
                    }
                    this.splitInput[1] = null;
                    this.splitInput[2] = null;
                } catch (AragornException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case Constants.DEADLINE:
                try {
                    splitDeadline = userInput.split(Constants.BYREGEX, 2);
                    this.splitInput[0] = splitDeadline[0].substring(8).trim();
                    if (this.splitInput[0].trim().isEmpty()) {
                        throw new AragornException(Constants.EMPTYDESCRIPTION);
                    }
                    this.splitInput[1] = splitDeadline[1].trim();
                    if (this.splitInput[1].trim().isEmpty()) {
                        throw new AragornException(Constants.EMPTYDEADLINE);
                    }
                    this.splitInput[2] = null;
                } catch (AragornException e) {
                    System.out.println(e.getMessage());
                } catch (NullPointerException e) {
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(Constants.INVALIDFORMAT);
                }
                break;

            case Constants.EVENT:
                try {
                    splitDeadline = userInput.split(Constants.FROMREGEX, 2);

                    this.splitInput[0] = splitDeadline[0].substring(5).trim();
                    if (this.splitInput[0].trim().isEmpty()) {
                        throw new AragornException(Constants.EMPTYDESCRIPTION);
                    }
                    splitEvent = splitDeadline[1].split(Constants.TOREGEX, 2);
                    this.splitInput[1] = splitEvent[0].trim();
                    if (this.splitInput[1].trim().isEmpty()) {
                        throw new AragornException(Constants.EMPTYEVENTSTART);
                    }
                    this.splitInput[2] = splitEvent[1].trim();
                    if (this.splitInput[2].trim().isEmpty()) {
                        throw new AragornException(Constants.EMPTYEVENTEND);
                    }
                } catch (AragornException e) {
                    System.out.println(e.getMessage());
                }  catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(Constants.INVALIDFORMAT);
                } catch (NullPointerException e) {
                    break;
                }
                break;
        }
    }

    public String[] getSplitInput() {
        return splitInput;
    }

}
