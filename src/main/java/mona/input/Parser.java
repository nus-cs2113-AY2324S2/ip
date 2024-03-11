package mona.input;

import mona.exception.MonaException;
import mona.output.ConsolePrint;
import mona.util.Constants;

/**
 * Parses and validates user input commands for the task manager application.
 * Stores the input commands in a String array, 'commandTypeAndParams'.
 * Elements of this array can be accessed via indexing.
 */
public class Parser {

    protected String[] commandTypeAndParams;
    protected String line;
    protected boolean isValidInput;
    protected InputValidator inputValidator;
    protected boolean isNotByeCommand;

    /**
     * Constructor for Parser. Initializes the 'commandTypeAndParams' String array, input line,
     * input validation status, and the input validator. Parses the input line.
     *
     * @param line The input line to be parsed.
     */
    public Parser(String line) {
        this.commandTypeAndParams = new String[4];
        this.line = line;
        this.isValidInput = true; // set to true initially, any error caught will set it to false
        this.isNotByeCommand = true; // set to true initially
        this.inputValidator = new InputValidator();

        this.parseInput(line);
    }

    public String[] getCommandTypeAndParams() {
        return commandTypeAndParams;
    }

    public boolean isNotByeCommand() {
        return isNotByeCommand;
    }

    public boolean isValidInput() {
        return isValidInput;
    }

    /**
     * Extracts the command type from the input line. The command type is the first word of the input line.
     *
     * @param line The input line from which the command type is to be extracted.
     */
    public void extractCommandTypeFromString(String line) {
        switch(line) {
        case("list"):
            commandTypeAndParams[Constants.INDEX_COMMAND_TYPE] = "list";
            return;
        case("bye"):
            commandTypeAndParams[Constants.INDEX_COMMAND_TYPE] = "bye";
            return;
        case("help"):
            commandTypeAndParams[Constants.INDEX_COMMAND_TYPE] = "help";
            return;
        default:
            try {
                int endIndex = line.indexOf(" ");
                this.commandTypeAndParams[Constants.INDEX_COMMAND_TYPE] = line.substring(0, endIndex).trim();
            } catch (StringIndexOutOfBoundsException e) {
                ConsolePrint.printErrorMessage(Constants.INVALID_COMMAND_ERROR_MESSAGE);
                isValidInput = false;
            }
        }
    }

    /**
     * Extracts details for 'mark', 'unmark', or 'delete' commands from the input line.
     * The detail to be extracted is a number, which corresponds to a specific task in the TaskList.
     *
     * @param line The input line from which the details are to be extracted.
     */
    public void extractDetailsFromMarkUnmarkDeleteString(String line) {
        try {
            int descriptionIndex = line.indexOf(" ");

            this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();

            inputValidator.checkMarkUnmarkDeleteCommand(commandTypeAndParams);
        } catch (MonaException e) {
            ConsolePrint.printErrorMessage(e.getMessage());
            isValidInput = false;
        }
    }

    /**
     * Extracts details for 'todo' commands from the input line. The detail to be extracted
     * is the description of the 'todo' command.
     *
     * @param line The input line from which the details are to be extracted.
     */
    public void extractDetailsFromTodoString(String line) {
        try {
            int descriptionIndex = line.indexOf(" ");

            this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();

            inputValidator.checkTodoCommand(commandTypeAndParams);
        } catch (MonaException e) {
            ConsolePrint.printErrorMessage(e.getMessage());
            isValidInput = false;
        }
    }

    /**
     * Extracts details for 'deadline' commands from the input line. The details to be extracted are
     * 1) the description of the deadline task, and
     * 2) the due date, prefixed with a '/by'.
     *
     * @param line The input line from which the details are to be extracted.
     */
    public void extractDetailsFromDeadlineString(String line) {
        try {
            int descriptionIndex = line.indexOf(" ");
            int deadlineIndex = line.indexOf(Constants.BY_PREFIX);

            this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] =
                    line.substring(descriptionIndex, deadlineIndex).trim();
            this.commandTypeAndParams[Constants.INDEX_DEADLINE] =
                    line.substring(deadlineIndex + Constants.BY_PREFIX.length()).trim();

            inputValidator.checkDeadlineCommand(commandTypeAndParams);
        } catch (StringIndexOutOfBoundsException e) {
            ConsolePrint.printErrorMessage(Constants.MISSING_BY_PREFIX_IN_DEADLINE_ERROR_MESSAGE);
            isValidInput = false;
        } catch (MonaException e) {
            ConsolePrint.printErrorMessage(e.getMessage());
            isValidInput = false;
        }
    }

    /**
     * Extracts details for 'event' commands from the input line. The details to be extracted are
     * 1) the description of the event task,
     * 2) the start date, prefixed with a '/from', and
     * 3) the end date, prefixed with a '/to'.
     *
     * @param line The input line from which the details are to be extracted.
     */
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

            inputValidator.checkEventCommand(commandTypeAndParams);
        } catch (StringIndexOutOfBoundsException e) {
            ConsolePrint.printErrorMessage(Constants.IMPROPER_FLAGS_IN_EVENT_ERROR);
            isValidInput = false;
        } catch (MonaException e) {
            ConsolePrint.printErrorMessage(e.getMessage());
            isValidInput = false;
        }
    }

    /**
     * Extracts details for 'find' commands from the input line. The detail to be extracted
     * is the keyword or phrase to be searched for.
     *
     * @param line The input line from which the details are to be extracted.
     */
    public void extractDetailsFromFindString(String line) {
        try {
            int descriptionIndex = line.indexOf(" ");

            this.commandTypeAndParams[Constants.INDEX_DESCRIPTION] = line.substring(descriptionIndex).trim();

            inputValidator.checkFindCommand(commandTypeAndParams);
        } catch (MonaException e) {
            ConsolePrint.printErrorMessage(e.getMessage());
            isValidInput = false;
        }
    }

    /**
     * Parses the input line and extracts the command type and details.
     *
     * @param line The input line to be parsed.
     */
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
        case("find"):
            extractDetailsFromFindString(line);
            break;
        case("list"):
            break;
        case("help"):
            break;
        case("bye"):
            this.isNotByeCommand = false;
            break;
        // default case is for invalid commands
        default:
            isValidInput = false;
            ConsolePrint.printErrorMessage(Constants.INVALID_COMMAND_ERROR_MESSAGE);
        }
    }
}
