package Casper;

/**
 * Parser is a class whose sole functionality is to verify the validity of the user's input and translate
 * them to the <code>Command</code> class to be executed.
 */

public class Parser {
    private static final String[] keywordList = {"bye", "list", "mark", "unmark",
            "deadline", "event", "todo", "delete", "find"};

    /**
     * Returns a <code>Command</code> based on the user's input.
     *
     * @param userInput The user's raw string input.
     * @return A <code>Command</code> based on <code>userInput</code>.
     * @throws CasperUnrecognizedKeywordException If the keyword in <code>userInput</code> is unrecognized.
     */
    public static Command parse(String userInput)
            throws CasperUnrecognizedKeywordException {
        validateInputKeyword(userInput);
        return handleKeywordRouting(userInput);
    }

    private static void validateInputKeyword(String userInput)
            throws CasperUnrecognizedKeywordException {
        String userInputKeyword = userInput.split(" ")[0];
        boolean isValidKeyword = false;
        for(String keyword : keywordList){
            if (keyword.equals(userInputKeyword)) {
                isValidKeyword = true;
                break;
            }
        }
        if(!isValidKeyword){
            throw new CasperUnrecognizedKeywordException(userInputKeyword);
        }
    }

    private static Command handleKeywordRouting(String userInput){
        String commandPrefix = userInput.split(" ")[0];
        switch (commandPrefix) {
        case "bye":
            return new ExitCommand(false, "exit");
        case "list":
        case "mark":
        case "unmark":
        case "find":
            return new EditCommand(true, commandPrefix, userInput);
        case "event":
        case "deadline":
        case "todo":
            return new AddCommand(true, commandPrefix, userInput);
        case "delete":
            return new DeleteCommand(true, userInput);
        }
        return new ExitCommand(false, "exception");
    }
}



//            case "bye":
//                return false;
//            case "list":
//                echoTaskList();
//                break;
//            case "mark":
//                //fallthrough
//            case "unmark":
//                handleMarkTask(userInput);
//                break;
//            case "event":
//                handleEvent(userInput);
//                break;
//            case "deadline":
//                handleDeadline(userInput);
//                break;
//            case "todo":
//                handleTodo(userInput);
//                break;
//            case "delete":
//                deleteTask(userInput);
//                break;
//            default:
//                break;
