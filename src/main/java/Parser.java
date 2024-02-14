public class Parser {
    public enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, BYE, UNKNOWN
    }

    public static Command parseCommand(String userInput) {
        String[] userInputList = userInput.trim().split(" ");
        if (userInputList.length == 0) {
            return Command.UNKNOWN;
        }
        String commandToken = userInputList[0].toUpperCase();
        Command returnValue;

        switch (commandToken) {
        case "TODO":
            returnValue = Command.TODO;
            break;
        case "DEADLINE":
            returnValue = Command.DEADLINE;
            break;
        case "EVENT":
            returnValue = Command.EVENT;
            break;
        case "LIST":
            returnValue = Command.LIST;
            break;
        case "BYE":
            returnValue = Command.BYE;
            break;
        case "MARK":
            returnValue = Command.MARK;
            break;
        case "UNMARK":
            returnValue = Command.UNMARK;
            break;
        default:
            returnValue = Command.UNKNOWN;
        }

        return returnValue;
    }

    public static String[] parseEvent(String eventString) throws Errors.InvalidEventException {
        int eventLength = "event ".length();
        int fromLength = "/from ".length();
        int toLength = "/to ".length();

        int eventIndex = eventString.indexOf("event ");
        int fromIndex = eventString.indexOf("/from ");
        int toIndex = eventString.indexOf("/to ");

        if (eventIndex == -1 || fromIndex == -1 || toIndex == -1) {
            throw new Errors.InvalidEventException();
        }

        eventIndex = eventIndex + eventLength;
        fromIndex = fromIndex + fromLength;
        toIndex = toIndex + toLength;

        String eventName = eventString.substring(eventIndex, fromIndex - fromLength);
        String startDateTime = eventString.substring(fromIndex, toIndex - toLength);
        String endDateTime = eventString.substring(toIndex);

        return new String[]{eventName, startDateTime, endDateTime};
    }

    public static String[] parseDeadline(String deadlineString) throws Errors.InvalidDeadlineException {
        int deadlineLength = "deadline ".length();
        int byLength = "/by ".length();

        int deadlineIndex = deadlineString.indexOf("deadline ");
        int byIndex = deadlineString.indexOf("/by ");

        if (deadlineIndex == -1 || byIndex == -1) {
            throw new Errors.InvalidDeadlineException();
        }

        deadlineIndex = deadlineIndex + deadlineLength;
        byIndex = byIndex + byLength;

        String deadlineName = deadlineString.substring(deadlineIndex, byIndex - byLength);
        String dueDate = deadlineString.substring(byIndex);

        return new String[]{deadlineName, dueDate};
    }

    public static String parseToDo(String todoString) throws Errors.InvalidTodoException {
        String[] parseInput = todoString.split(" ", 2);
        if (parseInput.length == 1) {
            throw new Errors.InvalidTodoException();
        }

        return parseInput[1];
    }

    /**
     * Parses the user input to determine if they want to mark or unmark a specified task
     *
     * @param markUnmarkString The input given by the user that either starts with mark or unmark
     * @return An object array that contains either {-1, null} if invalid input, or {index of item, true/false (mark or unmark)}
     */
    public static Object[] parseMarkUnmark(String markUnmarkString) throws Errors.InvalidMarkUnmarkException, Errors.InvalidMarkUnmarkIndexException {
        String[] markList = markUnmarkString.split(" ", 2);

        if (markList.length != 2) {
            throw new Errors.InvalidMarkUnmarkException();
        }

        int markItem = Integer.parseInt(markList[1]) - 1;
        String markAction = markList[0];

        if (markItem > TaskHandler.listCount) {
            throw new Errors.InvalidMarkUnmarkIndexException();
        }

        return new Object[]{markItem, markAction.equals("mark")};
    }
}
