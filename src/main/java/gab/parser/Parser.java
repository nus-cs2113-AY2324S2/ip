package gab.parser;

import gab.exception.GabException;
import gab.task.TaskList;
import gab.command.*;

/**
 * Parser class to parse inputs by the user
 */

public class Parser {
    public static final int TODO_START_INDEX = 5;
    public static final int DEADLINE_START_INDEX = 9;
    public static final int EVENT_START_INDEX = 6;

    /**
     * Parse input into todo function and check for exceptions
     *
     * @param task user input for adding task
     * @return to do command to be executed
     * @throws GabException error to be thrown when input is incomplete
     */

    public static Command parseToDo(String task) throws GabException {
        if (task.length() <= TODO_START_INDEX) {
            throw new GabException("Incomplete input! Correct usage: todo [Task name]");
        }
        String[] taskArray = task.trim().split("\\s+", 2);
        if (taskArray.length < 2) {
            throw new GabException("Todo task is empty! Correct usage: todo [task name]");
        }
        return new TodoCommand(taskArray[1]);
    }

    /**
     * Parse input into deadline function and check for exceptions
     *
     * task user input for adding deadline
     * @return deadline command to be executed
     * @throws GabException error to be thrown when input is incomplete
     */

    public static Command parseDeadline(String task) throws GabException {
        if (task.length() <= DEADLINE_START_INDEX) {
            throw new GabException("Incomplete input! Correct usage: deadline [Task name] /by [Due date]");
        }

        String deadlineSubstring = task.trim().substring(DEADLINE_START_INDEX);
        String[] deadlineInfo = deadlineSubstring.split(" /by");

        if (deadlineInfo.length == 0 || deadlineInfo[0].trim().isEmpty()) {
            throw new GabException("Missing task and deadline! Correct usage: deadline [Task name] /by [Due date]");
        } else if (deadlineInfo.length < 2) {
            throw new GabException("Missing deadline! Correct usage: deadline [Task name] /by [Due date]");
        } else if (deadlineInfo[1].trim().isEmpty()) {
            throw new GabException("Missing deadline! Correct usage: deadline [Task name] /by [Due date]");
        }

        String deadlineName = deadlineInfo[0].trim();
        String deadline = deadlineInfo[1].trim();
        return new DeadlineCommand(deadlineName, deadline);
    }

    /**
     * Parse input into event function and check for exceptions
     *
     * @param task user input for adding event
     * @return event command to be executed
     * @throws GabException error to be thrown when input is incomplete
     */

    public static Command parseEvent(String task) throws GabException {
        if (task.length() <= EVENT_START_INDEX) {
            throw new GabException("Incomplete input! Correct usage: event [Event name] /from [Start date] /to [End date]");
        }

        String eventSubstring = task.substring(EVENT_START_INDEX);
        String[] eventArray = eventSubstring.split(" /from", 2); // Limit split to 2 parts

        if (eventArray.length < 2) {
            throw new GabException("Missing '/from' statement! Correct Usage: event [Event Name] /from [Start Date] /to [End Date]");
        }

        String eventName = eventArray[0].trim(); // Trim whitespace

        String[] eventTimeArray = eventArray[1].split(" /to", 2); // Limit split to 2 parts
        if (eventTimeArray.length < 2) {
            throw new GabException("Missing '/to' statement! Correct Usage: event [Event Name] /from [Start Date] /to [End Date]");
        }

        String startDate = eventTimeArray[0].trim();
        String endDate = eventTimeArray[1].trim();

        return new EventCommand(eventName, startDate, endDate);
    }

    /**
     * Parse input for list
     *
     * @param taskDescription the input from the user
     * @return list command that list out tasks from taskList
     * @throws GabException thrown when 'list' is not used correctly
     */

    public static Command listTask(String taskDescription) throws GabException {
        String[] task = taskDescription.trim().split("\\s+");

        if (task.length > 1) {
            throw new GabException("Incorrect usage! Correct usage: list");
        }

        return new ListCommand();
    }

    /**
     * Parse input for bye
     *
     * @param taskDescription the input from the user
     * @return bye command which exits the program
     * @throws GabException thrown when 'bye' is not used correctly
     */

    public static Command exitBot (String taskDescription) throws GabException {
        String[] task = taskDescription.trim().split("\\s+");

        if (task.length > 1) {
            throw new GabException("Incorrect usage! Correct usage: bye");
        }
        return new ByeCommand();
    }

    /**
     * Parse input into mark function and check for exceptions
     *
     * @param taskDescription user input for marking task
     * @param taskList array list of task
     * @return markCommand that marks a task
     * @throws GabException thrown when input is incomplete for erroneous
     */

    public static Command markTask(String taskDescription, TaskList taskList) throws GabException {
        String[] task = taskDescription.trim().split("\\s+");

        if (task.length < 2) {
            throw new GabException("Missing task to mark! Correct usage: mark [Task Number]");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(task[1]);
        } catch (NumberFormatException e) {
            throw new GabException("Task index to mark is not an integer! Please provide an integer");
        }

        if (taskIndex > taskList.taskList.size()) {
            throw new GabException("Task is not found!");
        }

        return new MarkCommand(taskIndex); // Convert taskIndex back to String for MarkCommand constructor
    }

    /**
     * Parse input into un-mark function and check for exceptions
     *
     * @param taskDescription user input for un-marking task
     * @param taskList array list of task
     * @return un-mark Command that marks a task
     * @throws GabException thrown when input is incomplete for erroneous
     */

    public static Command UnmarkTask(String taskDescription, TaskList taskList) throws GabException {
        String[] task = taskDescription.trim().split("\\s+");

        if (task.length < 2) {
            throw new GabException("Missing task to unmark! Correct usage: mark [Task Number]");
        }
        String taskIndexString = task[1];
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexString);
        } catch (NumberFormatException e) {
            throw new GabException("Task index to unmark is not integer! Please provide an integer");
        }

        if (taskIndex > taskList.taskList.size()) {
            throw new GabException("Task is not found!");
        }
        return new UnmarkCommand(taskIndexString);
    }


    /**
     * Parse user input into delete task command and check for exceptions
     *
     * @param taskDescription user input when deleting task
     * @param taskList array list of task
     * @return return delete command which deletes a task
     * @throws GabException thrown when input is incomplete or erroneous
     */

    public static Command DeleteTask (String taskDescription, TaskList taskList) throws GabException {
        String[] task = taskDescription.trim().split("\\s+");

        if (task.length < 2) {
            throw new GabException("Missing task to delete! Correct usage: delete [Task Number]");
        }
        String deleteIndexString = task[1];
        int deleteIndex;

        try {
            deleteIndex = Integer.parseInt(deleteIndexString);
        } catch (NumberFormatException e) {
            throw new GabException("Task index to delete is not integer! Please provide an integer");
        }

        if (deleteIndex > taskList.taskList.size() || deleteIndex <= 0) {
            throw new GabException("Task not found within the list!");
        }
        return new DeleteCommand(deleteIndex);
    }

    /**
     * Parse user inputs to find a keyword in a task
     *
     * @param taskDescription user input when finding task
     * @return find command which displays task that matches input
     * @throws GabException thrown when input is incomplete
     */

    public static Command FindTask (String taskDescription) throws GabException {
        String[] task = taskDescription.trim().split("\\s+");

        if (task.length < 2) {
            throw new GabException("Missing task to find! Correct usage: find [Task Number]");
        }
        String taskToFind = task[1];
        return new FindCommand(taskToFind);
    }
}




