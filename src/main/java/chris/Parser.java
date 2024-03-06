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
            default:
                return new Command(description);
        }
    }

    public String getCommandString(String input) {
        return input.split(" ")[0];
    }

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
            default:
                description = new String[] {""};
                return description;
        }
    }

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
