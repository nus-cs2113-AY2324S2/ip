package mona.input;

import mona.exception.MonaException;
import mona.util.Constants;

public class InputParser {
    protected String[] commandTypeAndParams;
    protected String line;
    protected boolean isValidInput;
    protected InputValidator inputValidator;
    public InputParser(String line) {
        this.commandTypeAndParams = new String[4];
        this.line = line;
        this.isValidInput = true; // set to true initially, any error caught will set it to false
        this.inputValidator = new InputValidator();

        this.parseInput(line);
    }

    public String[] getCommandTypeAndParams() {
        return commandTypeAndParams;
    }

    public boolean isValidInput() {
        return isValidInput;
    }

    public void extractCommandTypeFromString(String line) {
        // checking specifically for "list", as it is a single word command
        if (line.equals("list")) {
            commandTypeAndParams[Constants.INDEX_COMMAND_TYPE] = "list";
            return;
        }

        try {
            int endIndex = line.indexOf(" ");

            this.commandTypeAndParams[Constants.INDEX_COMMAND_TYPE] = line.substring(0, endIndex).trim();

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid command detected!");
            isValidInput = false;
        }
    }
    public void extractDetailsFromMarkUnmarkDeleteString(String line) {
        try {
            int descriptionIndex = line.indexOf(" ");

            this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();

            inputValidator.markUnmarkDeleteCommandChecker(commandTypeAndParams);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! This description of a mark/unmark command cannot be empty");
            isValidInput = false;
        } catch (MonaException e) {
            System.out.println(e.getMessage());
            isValidInput = false;
        }
    }
    public void extractDetailsFromTodoString(String line) {
        try {
            int descriptionIndex = line.indexOf(" ");

            this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();

            inputValidator.todoCommandChecker(commandTypeAndParams);
        } catch (MonaException e) {
            System.out.println(e.getMessage());
            isValidInput = false;
        }
    }
    public void extractDetailsFromDeadlineString(String line) {

        try {
            int descriptionIndex = line.indexOf(" ");
            int deadlineIndex = line.indexOf(Constants.BY_PREFIX);

            this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex, deadlineIndex).trim();
            this.commandTypeAndParams[Constants.INDEX_DEADLINE] = line.substring(deadlineIndex
                    + Constants.BY_PREFIX.length()).trim();

            inputValidator.deadlineCommandChecker(commandTypeAndParams);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Remember to key in /by to let me know when the deadline is!");
            isValidInput = false;
        } catch (MonaException e) {
            System.out.println(e.getMessage());
            isValidInput = false;
        }
    }
    public void extractDetailsFromEventString(String line) {
        try {
            int descriptionIndex = line.indexOf(" ");
            int fromIndex = line.indexOf(Constants.FROM_PREFIX);
            int toIndex = line.indexOf(Constants.TO_PREFIX);

            this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex, fromIndex).trim();
            this.commandTypeAndParams[Constants.INDEX_FROM_DATE] = line.substring(fromIndex
                    + Constants.FROM_PREFIX.length(), toIndex).trim();
            this.commandTypeAndParams[Constants.INDEX_TO_DATE] = line.substring(toIndex
                    + Constants.TO_PREFIX.length()).trim();

            inputValidator.eventCommandChecker(commandTypeAndParams);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid input format detected!");
            isValidInput = false;
        } catch (MonaException e) {
            System.out.println(e.getMessage());
            isValidInput = false;
        }
    }
    public void parseInput(String line) {
        extractCommandTypeFromString(line);

        // if Command Type cannot be detected, stop parsing input for further details
        if (!isValidInput) {
            return;
        }

        String commandType = commandTypeAndParams[Constants.INDEX_COMMAND_TYPE];

        switch(commandType) {
        case ("mark"):
            //fallthrough
        case ("unmark"):
            //fallthrough
        case("delete"):
            extractDetailsFromMarkUnmarkDeleteString(line);
            break;
        case ("todo"):
            extractDetailsFromTodoString(line);
            break;
        case ("deadline"):
            extractDetailsFromDeadlineString(line);
            break;
        case ("event"):
            extractDetailsFromEventString(line);
            break;
        case("list"):
            break;
        // default case is for invalid commands
        default:
            isValidInput = false;
            System.out.println("Invalid command detected!");
        }

    }
}
