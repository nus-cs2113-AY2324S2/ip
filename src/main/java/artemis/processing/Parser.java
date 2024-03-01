package artemis.processing;

import artemis.errors.Errors;
import artemis.tasks.Deadline;
import artemis.tasks.Event;
import artemis.tasks.Task;
import artemis.tasks.ToDo;

public class Parser {
    public enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, BYE, SAVE, DELETE, FIND, UNKNOWN
    }

    public static Command parseCommand(String userInput) {
        String[] userInputList = userInput.trim().split(" ");
        if (userInputList.length == 0) {
            return Command.UNKNOWN;
        }
        String commandToken = userInputList[0].toUpperCase();
        Command returnValue = switch (commandToken) {
            case "TODO" -> Command.TODO;
            case "DEADLINE" -> Command.DEADLINE;
            case "EVENT" -> Command.EVENT;
            case "LIST" -> Command.LIST;
            case "BYE" -> Command.BYE;
            case "MARK" -> Command.MARK;
            case "UNMARK" -> Command.UNMARK;
            case "DELETE" -> Command.DELETE;
            case "SAVE" -> Command.SAVE;
            case "FIND" -> Command.FIND;
            default -> Command.UNKNOWN;
        };

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
    public static Object[] parseMarkUnmark(String markUnmarkString, int taskListSize) throws Errors.InvalidMarkUnmarkException, Errors.InvalidMarkUnmarkIndexException {
        String[] markList = markUnmarkString.split(" ", 2);

        if (markList.length != 2) {
            throw new Errors.InvalidMarkUnmarkException();
        }

        int markItem = Integer.parseInt(markList[1]) - 1;
        String markAction = markList[0];

        if (markItem > taskListSize) {
            throw new Errors.InvalidMarkUnmarkIndexException();
        }

        return new Object[]{markItem, markAction.equals("mark")};
    }

    public static int parseDelete(String deleteString, int taskListSize) throws Errors.InvalidDeleteException, Errors.TaskNotFoundException {
        String[] deleteList = deleteString.split(" ", 2);
        if (deleteList.length != 2) {
            throw new Errors.InvalidDeleteException();
        }

        int deleteItem = Integer.parseInt(deleteList[1]) - 1;

        if (deleteItem > taskListSize) {
            throw new Errors.TaskNotFoundException();
        }

        return deleteItem;
    }
    
    public static Task parseSaveData(String[] currentTaskArray, String taskName, boolean isDone) throws Errors.CorruptedSaveException {
        Task currentTask;

        // to dos: 2 pipes
        // deadlines: 3 pipes
        // events: 4 pipes
        if (currentTaskArray.length == 3) {
            ToDo newToDo = new ToDo(taskName);
            newToDo.markAsDone(isDone);

            currentTask = newToDo;
        } else if (currentTaskArray.length == 4) {
            String dueDate = currentTaskArray[3];
            Deadline newDeadline = new Deadline(taskName, dueDate);
            newDeadline.markAsDone(isDone);

            currentTask = newDeadline;
        } else if (currentTaskArray.length == 5) {
            String startDateTime = currentTaskArray[3];
            String endDateTime = currentTaskArray[4];

            Event newEvent = new Event(taskName, startDateTime, endDateTime);
            newEvent.markAsDone(isDone);

            currentTask = newEvent;
        } else {
            throw new Errors.CorruptedSaveException();
        }
        return currentTask;
    }

    public static String parseFind(String findString) throws Errors.InvalidFindException {
        String[] findList = findString.split(" ", 2);
        if (findList.length == 1) {
            throw new Errors.InvalidFindException();
        }

        return findList[1];
    }
}
