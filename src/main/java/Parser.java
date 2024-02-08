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

    public static String[] parseEvent(String eventString) {
        int eventLength = "event ".length();
        int fromLength = "/from ".length();
        int toLength = "/to ".length();

        int eventIndex = eventString.indexOf("event ") + eventLength;
        int fromIndex = eventString.indexOf("/from ") + fromLength;
        int toIndex = eventString.indexOf("/to ") + toLength;

        String eventName = eventString.substring(eventIndex, fromIndex - fromLength);
        String startDateTime = eventString.substring(fromIndex, toIndex - toLength);
        String endDateTime = eventString.substring(toIndex);

        return new String[]{eventName, startDateTime, endDateTime};
    }

    public static String[] parseDeadline(String deadlineString) {
        int deadlineLength = "deadline ".length();
        int byLength = "/by ".length();

        int deadlineIndex = deadlineString.indexOf("deadline ") + deadlineLength;
        int byIndex = deadlineString.indexOf("/by ") + byLength;

        String deadlineName = deadlineString.substring(deadlineIndex, byIndex - byLength);
        String dueDate = deadlineString.substring(byIndex);

        return new String[]{deadlineName, dueDate};
    }

    public static String parseToDo(String todoString) {
        String[] parseInput = todoString.split(" ", 2);

        return parseInput[1];
    }

    /**
     * Parses the user input to determine if they want to mark or unmark a specified task
     *
     * @param markUnmarkString The input given by the user that either starts with mark or unmark
     * @return An object array that contains either {-1, null} if invalid input, or {index of item, true/false (mark or unmark)}
     */
    public static Object[] parseMarkUnmark(String markUnmarkString) {
        String[] markList = markUnmarkString.split(" ", 2);

        if (markList.length != 2) {
            System.out.println("[artemis]: please enter \"[mark/unmark] <list item number>\".");
            return new Object[]{-1, null};
        }

        int markItem = Integer.parseInt(markList[1]) - 1;
        String markAction = markList[0];

        if (markItem > TaskHandler.listCount) {
            System.out.println("list item number given is invalid!");
            return new Object[]{-1, null};
        }

        return new Object[]{markItem, markAction.equals("mark")};
    }
}
