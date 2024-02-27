package logic;

import exceptions.DeadlineNoByDateTimeException;
import exceptions.EventNoFromDateTimeException;
import exceptions.EventNoToDateTimeException;
import exceptions.EventToBeforeFromException;
import exceptions.TaskNoNameException;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * Class used to parse user input when adding tasks
 */
public abstract class Parser {
    public static final int TODO_LENGTH= 5;
    public static final int DEADLINE_LENGTH = 9;
    public static final int EVENT_LENGTH = 6;
    public static final int BY_LENGTH = 4;
    public static final int FROM_LENGTH = 6;
    public static final int TO_LENGTH = 4;

    /**
     * Returns the ToDo-type task specified in the user input. Determines the name of the task from the input
     *
     * @param input The user input specifying the task
     * @return ToDo-type task
     */
    public static ToDo processToDo(String input) {
        String taskName;
        taskName = input.substring(TODO_LENGTH);
        return new ToDo(taskName);
    }

    /**
     * Returns the Deadline-type task specified in the user input. Determines the name and due date/time of the
     * task from the input
     *
     * @param input The user input specifying the task
     * @return Deadline-type task
     * @throws Exception If task lacks a name
     */
    public static Deadline processDeadline(String input) throws Exception {
        if (!(input.contains("/by "))) {
            throw new DeadlineNoByDateTimeException();
        }
        String taskName;
        int firstBackslashIndex = input.indexOf("/");
        if (firstBackslashIndex == DEADLINE_LENGTH) {
            // occurs when task is not given a name
            throw new TaskNoNameException();
        }
        taskName = input.substring(DEADLINE_LENGTH, firstBackslashIndex - 1);
        int byWhenIndex = firstBackslashIndex + BY_LENGTH;
        String byWhen = input.substring(byWhenIndex);
        return new Deadline(taskName, byWhen);
    }

    /**
     * Returns the Event-type task specified in the user input. Determines the name, start date/time and
     * end date/time of the task rom the input
     *
     * @param input The user input specifying the task
     * @return Event-type task
     * @throws Exception If task lacks a name
     */
    public static Event processEvent(String input) throws Exception {
        if (!(input.contains("/from "))) {
            throw new EventNoFromDateTimeException();
        }
        if (!(input.contains("/to "))) {
            throw new EventNoToDateTimeException();
        }
        if (input.indexOf("/to ") < input.indexOf("/from ")) {
            throw new EventToBeforeFromException();
        }
        String taskName;
        int firstBackslashIndex = input.indexOf("/");
        if (firstBackslashIndex == EVENT_LENGTH) {
            // occurs when task is not given a name
            throw new TaskNoNameException();
        }
        taskName = input.substring(EVENT_LENGTH, firstBackslashIndex - 1);
        int fromWhenIndex = firstBackslashIndex + FROM_LENGTH;
        int secondBackslashIndex = input.indexOf("/", fromWhenIndex + 1);
        int toWhenIndex = secondBackslashIndex + TO_LENGTH;
        String fromWhen = input.substring(fromWhenIndex, secondBackslashIndex - 1);
        String toWhen = input.substring(toWhenIndex);
        return new Event(taskName, fromWhen, toWhen);
    }
}
