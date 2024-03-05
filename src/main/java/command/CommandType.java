package command;

import exception.InputException;
import tool.ResponseManager;

public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find");

    private final String type;
    CommandType(String type) {
        this.type = type;
    }

    /**
     * This method returns the type of the command
     * @return String containing the type of the command
     */
    public String getType() {
        return type;
    }
    
    /**
     * This method analyses the type of the command
     * @param input the user input
     * @return the type of the command
     * @throws InputException if the command is not recognised
     */
    public static CommandType analyseType(String input) throws InputException {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.type.equals(input)) {
                return commandType;
            }
        }
        throw new InputException(ResponseManager.COMMAND_ERROR);
    }
}
