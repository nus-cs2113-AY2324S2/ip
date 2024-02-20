package joe.util;

import joe.JoeException;
import joe.task.Deadline;
import joe.task.Event;
import joe.task.Task;
import joe.task.ToDo;

public class FileParser {
    protected static final char TODO_SYMBOL = 't';
    protected static final char DEADLINE_SYMBOL = 'd';
    protected static final char EVENT_SYMBOL = 'e';
    protected static final char DONE_SYMBOL = 'X';
    protected static final String SEPARATOR = "#";

    public static Task readTaskData(String taskData) throws JoeException {
        char taskSymbol = taskData.charAt(0);
        boolean isDone = taskData.charAt(2) == DONE_SYMBOL;
        String taskInput = taskData.substring(4);
        if (taskInput.isEmpty()) {
            throw new JoeException();
        }

        Task task;
        switch (taskSymbol) {
        case TODO_SYMBOL:
            task = new ToDo(taskInput);
            break;
        case DEADLINE_SYMBOL:
            String[] deadlineData = splitData(taskInput);
            if (deadlineData.length != 2) {
                throw new JoeException();
            }
            task = new Deadline(deadlineData[0], deadlineData[1]);
            break;
        case EVENT_SYMBOL:
            String[] eventData = splitData(taskInput);
            if (eventData.length != 3) {
                throw new JoeException();
            }
            task = new Event(eventData[0], eventData[1], eventData[2]);
            break;
        default:
            throw new JoeException();
        }

        task.setDone(isDone);
        return task;
    }

    protected static String[] splitData(String data) {
        return data.split(SEPARATOR);
    }

    /**
     * Generates file data from a ToDo task
     * in the form "t#MARK STATUS#TASKNAME"
     *
     * @param taskInfo String of a ToDo task status
     * @return String of parsed data to used to write
     */
    public static String getTodoData(String taskInfo) {
        String doneSymbol = taskInfo.contains("[X]") ? String.valueOf(DONE_SYMBOL) : "o";
        String taskName = taskInfo.substring(taskInfo.indexOf("] ") + 1).trim();

        return TODO_SYMBOL + SEPARATOR + doneSymbol + SEPARATOR + taskName;
    }

    /**
     * Generates file data from a Deadline task
     * in the form "d#MARK STATUS#TASKNAME#DEADLINE DATE"
     *
     * @param taskInfo String of a Deadline task status
     * @return String of parsed data to used to write
     */
    public static String getDeadlineData(String taskInfo) {
        String doneSymbol = taskInfo.contains("[X]") ? String.valueOf(DONE_SYMBOL) : "o";
        String taskName = taskInfo.substring(taskInfo.indexOf("] ") + 1, taskInfo.indexOf("(by:")).trim();
        String deadlineTime = taskInfo.substring(taskInfo.indexOf("(by: ") + 5, taskInfo.lastIndexOf(")")).trim();

        return DEADLINE_SYMBOL + SEPARATOR + doneSymbol + SEPARATOR + taskName + SEPARATOR + deadlineTime;
    }

    /**
     * Generates file data from an Event task
     * in the form "e#MARK STATUS#TASKNAME#START DATE#END DATE"
     *
     * @param taskInfo String of an Event task status
     * @return String of parsed data to used to write
     */
    public static String getEventData(String taskInfo) {
        String doneSymbol = taskInfo.contains("[X]") ? String.valueOf(DONE_SYMBOL) : "o";
        String taskName = taskInfo.substring(taskInfo.indexOf("] ") + 1, taskInfo.indexOf("(from: ")).trim();
        String eventStart = taskInfo.substring(taskInfo.indexOf("(from: ") + 7, taskInfo.indexOf(" to: "));
        String eventEnd = taskInfo.substring(taskInfo.indexOf(" to: ") + 5, taskInfo.lastIndexOf(")"));

        return EVENT_SYMBOL + SEPARATOR + doneSymbol + SEPARATOR + taskName + SEPARATOR + eventStart + SEPARATOR + eventEnd;
    }
}
