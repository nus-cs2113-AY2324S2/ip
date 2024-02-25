package Casper;


public class Parser {
    private static final String[] keywordList = {"bye", "list", "mark", "unmark",
            "deadline", "event", "todo", "delete", "find"};

    public static Command parse(String userInput) throws CasperUnrecognizedKeywordException {
        validateInputKeyword(userInput);
        return handleKeywordRouting(userInput);
    }

    public static void validateInputKeyword(String userInput) throws CasperUnrecognizedKeywordException {
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

    public static Command handleKeywordRouting(String userInput){
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
