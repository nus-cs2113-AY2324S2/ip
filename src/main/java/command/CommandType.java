package command;

import exception.InputException;
import ui.ResponseManager;

public enum CommandType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find"),
    HELP("help");

    private final String type;
    CommandType(String type) {
        this.type = type;
    }
    
    /**
     * Analyzes the type of the command based on the input string.
     *
     * @param input the user input.
     * @return the type of the command.
     * @throws InputException if the command is not recognized.
     */
    public static CommandType analyseType(String input) throws InputException {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.type.equals(input)) {
                return commandType;
            }
        }
        throw new InputException(ResponseManager.COMMAND_ERROR);
    }

    @Override
    public String toString() {
        return type;
    }
}
