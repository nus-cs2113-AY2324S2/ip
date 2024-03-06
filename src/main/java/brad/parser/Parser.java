package brad.parser;

import brad.exceptions.emptyArgumentException;
import brad.exceptions.invalidCommandException;

public class Parser {
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String BYE = "bye";
    private Command command;
    private String userInput = "";
    private boolean hasArgument = true;

    public Command getCommand() {
        return command;
    }

    public String getUserInput() {
        return userInput;
    }

    /**
     * Parses user command and interprets it
     * @param input full user input
     * @throws ArrayIndexOutOfBoundsException if accidentally accessed an invalid array index
     * @throws emptyArgumentException if no command is given
     * @throws invalidCommandException if command is invalid based on command types
     */
    public void parseCommand(String input) throws
            ArrayIndexOutOfBoundsException, emptyArgumentException, invalidCommandException {
        String[] splitInput = input.split(" ", 2);
        command = interpretInput(splitInput[0]);
        if (hasArgument) {
            if (splitInput[1].isEmpty()) {
                throw new emptyArgumentException();
            }
            userInput = splitInput[1];
        }
    }

    /**
     * Identify the type of user command
     * @param input user command input
     * @return corresponding command
     * @throws invalidCommandException if command is invalid based on command types
     */
    private Command interpretInput(String input) throws
            invalidCommandException {
        switch (input) {
            case LIST:
                hasArgument = false;
                return Command.LIST;
            case MARK:
                return Command.MARK;
            case UNMARK:
                return Command.UNMARK;
            case TODO:
                return Command.TODO;
            case DEADLINE:
                return Command.DEADLINE;
            case EVENT:
                return Command.EVENT;
            case DELETE:
                return Command.DELETE;
            case FIND:
                return Command.FIND;
            case BYE:
                hasArgument = false;
                return Command.BYE;
            default:
                hasArgument = false;
                throw new invalidCommandException();
        }
    }
}
