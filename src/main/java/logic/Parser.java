package logic;

import exceptions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Parser {
    public static final int TODO_LENGTH= 5;
    public static final int DEADLINE_LENGTH = 9;
    public static final int EVENT_LENGTH = 6;
    public static final int BY_LENGTH = 4;
    public static final int FROM_LENGTH = 6;
    public static final int TO_LENGTH = 4;

    public static ToDo processToDo(String taskToAdd) {
        String taskName;
        taskName = taskToAdd.substring(TODO_LENGTH);
        return new ToDo(taskName);
    }

    private static Deadline processDeadline(String taskToAdd) throws Exception {
        if (!(taskToAdd.contains("/by "))) {
            throw new DeadlineNoByDateTimeException();
        }
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        if (firstBackslashIndex == DEADLINE_LENGTH) {
            // occurs when task is not given a name
            throw new TaskNoNameException();
        }
        taskName = taskToAdd.substring(DEADLINE_LENGTH, firstBackslashIndex - 1);
        int byWhenIndex = firstBackslashIndex + BY_LENGTH;
        String byWhen = taskToAdd.substring(byWhenIndex);
        return new Deadline(taskName, byWhen);
    }

    private static Event processEvent(String taskToAdd) throws Exception {
        if (!(taskToAdd.contains("/from "))) {
            throw new EventNoFromDateTimeException();
        }
        if (!(taskToAdd.contains("/to "))) {
            throw new EventNoToDateTimeException();
        }
        if (taskToAdd.indexOf("/to ") < taskToAdd.indexOf("/from ")) {
            throw new EventToBeforeFromException();
        }
        String taskName;
        int firstBackslashIndex = taskToAdd.indexOf("/");
        if (firstBackslashIndex == EVENT_LENGTH) {
            // occurs when task is not given a name
            throw new TaskNoNameException();
        }
        taskName = taskToAdd.substring(EVENT_LENGTH, firstBackslashIndex - 1);
        int fromWhenIndex = firstBackslashIndex + FROM_LENGTH;
        int secondBackslashIndex = taskToAdd.indexOf("/", fromWhenIndex + 1);
        int toWhenIndex = secondBackslashIndex + TO_LENGTH;
        String fromWhen = taskToAdd.substring(fromWhenIndex, secondBackslashIndex - 1);
        String toWhen = taskToAdd.substring(toWhenIndex);
        return new Event(taskName, fromWhen, toWhen);
    }
}
