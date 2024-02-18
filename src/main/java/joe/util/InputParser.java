package joe.util;

import joe.JoeException;
import joe.task.Task;
import joe.task.ToDo;
import joe.task.Event;
import joe.task.Deadline;

public class InputParser {
    protected static final String FLAG_INDICATOR = "/";
    protected static final String DEADLINE_FLAG =  FLAG_INDICATOR + "by";
    protected static final String EVENT_START_FLAG = FLAG_INDICATOR + "from";
    protected static final String EVENT_END_FLAG = FLAG_INDICATOR + "to";


    public static String getCommand(String input) {
        if (!input.contains(" ")) {
            return input;
        }
        return input.substring(0, input.indexOf(" "));
    }

    public static String getMessage(String input) {
        if (!input.contains(" ")) {
            return "";
        }
        return input.substring(input.indexOf(" ")).trim();
    }

    public static String getTaskName(String message) throws JoeException {
        if (!message.contains(FLAG_INDICATOR)) {
            throw new JoeException();
        }
        return message.substring(0, message.indexOf(FLAG_INDICATOR)).trim();
    }

    public static String getDeadlineTime(String message) throws JoeException{
        if (!message.contains(DEADLINE_FLAG)) {
            throw new JoeException();
        }
        return message.substring(message.indexOf(DEADLINE_FLAG)).replace(DEADLINE_FLAG, "").trim();
    }

    public static String[] getEventTime(String message) throws JoeException {
        if (!message.contains(EVENT_START_FLAG) || !message.contains(EVENT_END_FLAG)) {
            throw new JoeException();
        }
        String[] eventDurations = new String[2];
        int startIndex = message.indexOf(EVENT_START_FLAG);
        int endIndex = message.indexOf(EVENT_END_FLAG);
        eventDurations[0] = message.substring(startIndex, endIndex).replace(EVENT_START_FLAG, "").trim();
        eventDurations[1] = message.substring(endIndex).replace(EVENT_END_FLAG, "").trim();
        return eventDurations;
    }

    public static Task readTaskData(String taskData) throws JoeException {
        char taskSymbol = taskData.charAt(0);
        boolean isDone = taskData.charAt(1) == 'X';
        String taskInput = taskData.substring(2);

        Task task = null;
        switch (taskSymbol) {
        case 't':
            task = new ToDo(taskInput);
            task.setDone(isDone);
            break;
        case 'd':
            String deadlineName = getTaskName(taskInput);
            String deadlineTime = getDeadlineTime(taskInput);
            task = new Deadline(deadlineName, deadlineTime);
            task.setDone(isDone);
            break;
        case 'e':
            String eventName = getTaskName(taskInput);
            String[] eventTime = getEventTime(taskInput);
            task = new Event(eventName, eventTime[0], eventTime[1]);
            task.setDone(isDone);
            break;
        }

        return task;
    }
}
