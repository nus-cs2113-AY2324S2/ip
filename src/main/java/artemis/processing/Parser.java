package artemis.processing;

import artemis.errors.Errors;
import artemis.tasks.Deadline;
import artemis.tasks.Event;
import artemis.tasks.Task;
import artemis.tasks.ToDo;

public class Parser {
    public enum Command {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, BYE, SAVE, DELETE, UNKNOWN
    }

    /**
     * Parses the input that the user has entered into commands
     *
     * @param userInput Raw user input string taken from Scanner
     * @return Command: the enum of commands (see above)
     */
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
        case "DELETE":
            returnValue = Command.DELETE;
            break;
        case "SAVE":
            returnValue = Command.SAVE;
            break;
        default:
            returnValue = Command.UNKNOWN;
        }

        return returnValue;
    }

    /**
     * Parses event commands to extract the event title, start date time and end date time parameters
     *
     * @param eventString Raw event string given by the user
     * @return A string array that consists of the event title, start date time and end date time
     * @throws Errors.InvalidEventException If the given input does not contain the proper parameters for the event command
     */
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

    /**
     * Parses deadline commands to extract the task title and the due date of the task from the given input
     *
     * @param deadlineString Raw deadline string given by the user
     * @return A string array that consists of the deadline title and due date
     * @throws Errors.InvalidDeadlineException If the given input does not contain the proper parameters for the deadline command
     */
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

    /**
     * Parses to do commands to extract the task title
     *
     * @param todoString Raw deadline string given by the user
     * @return A string of the task title
     * @throws Errors.InvalidTodoException If the given input does not contain the proper parameters for the to do command
     */
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
     * @param taskListSize The size of the assigned task list
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

    /**
     * Parses the deletion of a task in the task list
     *
     * @param deleteString The string of the task to be deleted
     * @param taskListSize  The size of the assigned task list
     * @return An integer of the index of the task to be deleted in the list
     * @throws Errors.InvalidDeleteException If no index was given
     * @throws Errors.TaskNotFoundException If the index of the task to be deleted exceeds the number of tasks in the list
     */
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

    /**
     * Parses the data in the save file to be loaded into memory
     *
     * @param currentTaskArray The current array to be parsed
     * @param taskName The task name to be created
     * @param isDone Boolean if the task has been completed
     * @return Task Class that has been parsed
     * @throws Errors.CorruptedSaveException If the format of the data is not recognized
     */
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
}
