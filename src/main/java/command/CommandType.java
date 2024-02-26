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
    DELETE("delete");

    private final String type;
    CommandType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public static CommandType analyseType(String input) throws InputException {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.type.equals(input)) {
                return commandType;
            }
        }
        throw new InputException(ResponseManager.COMMAND_ERROR);
    }
}
