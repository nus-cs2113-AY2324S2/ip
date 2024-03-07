package chris;

import chris.commands.*;
import chris.customexceptions.*;

public class Parser {
    public Parser() {}
    public Command parse(String input) throws customExceptions{
        String commandString = getCommandString(input);
        String[] description = getDescription(input, commandString);
        return getCommand(commandString, description);
    }

    /**
     * Returns a Command object that can be executed.
     * @param commandString command to be executed as a string
     * @param description array of strings that contains information for use when executing the command
     * @return Command object
     */
    public Command getCommand(String commandString, String[] description) {
        switch (commandString) {
            case "quit":
                return new quitCommand(description);
            case "todo":
                return new toDoCommand(description);
            case "deadline":
                return new deadlineCommand(description);
            case "event":
                return new eventCommand(description);
            case "delete":
                return new deleteCommand(description);
            case "list":
                return new listCommand(description);
            case "mark":
                return new markCommand(description);
            case "find":
                return new findCommand(description);
            default:
                return new Command(description);
        }
    }

    public String getCommandString(String input) {
        return input.split(" ")[0];
    }

    /**
     * Given the whole user input, this function removes the command from the front of the input,
     * then checks and splits the description accordingly.
     * @param input User input string
     * @param commandString command to be executed as a string
     * @return array of strings that will be used when executing command
     * @throws customExceptions if the input does not match command requirements
     */
    public String[] getDescription(String input, String commandString) throws customExceptions{
        String[] description;
        input = input.trim();
        switch (commandString) {
            case "todo":
                if (input.length() < 6) {
                    throw new illegalToDoInput();
                }
                description = new String[] {input.substring(5).trim()};
                return description;
            case "deadline":
                if (input.length() < 10) {
                    throw new illegalDeadlineInput();
                }
                description = input.substring(9).split("/by");
                checkDescription(description, commandString);
                return description;
            case "event":
                if (input.length() < 7) {
                    throw new illegalEventInput();
                }
                description = input.substring(6).split("/from|/to");
                checkDescription(description, commandString);
                return description;
            case "delete":
                if (input.length() < 8) {
                    throw new illegalTaskNumberInput();
                }
                description = new String[] {input.substring(7)};
                checkDescription(description, commandString);
                return description;
            case "mark":
                if (input.length() < 6) {
                    throw new illegalTaskNumberInput();
                }
                description = new String[] {input.substring(5)};
                checkDescription(description, commandString);
                return description;
            case "find":
                if (input.length() < 6) {
                    throw new illegalFindInput();
                }
                description = new String[] {input.substring(5)};
                return description;
            default:
                description = new String[] {""};
                return description;
        }
    }

    /**
     * This function checks that the description is in the correct format and that there is a correct number of
     * arguments to be passed into execute()
     * @param description array of strings to be arguments
     * @param commandString command to be executed as a string
     * @throws customExceptions if the input does not match command requirements
     */
    public void checkDescription(String[] description, String commandString) throws customExceptions {
        switch(commandString) {
            case "delete":
            case "mark":
                if (description[0].length() != 1) {
                    throw new illegalTaskNumberInput();
                }
                break;
            case "deadline":
                if (description.length != 2) {
                    throw new illegalDeadlineInput();
                }
                break;
            case "event":
                if (description.length != 3) {
                    throw new illegalEventInput();
                }
        }
    }
}
