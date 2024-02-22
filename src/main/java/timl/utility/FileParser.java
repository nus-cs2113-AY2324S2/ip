package timl.utility;

import timl.exceptions.TimException;
import timl.task.Deadline;
import timl.task.Todo;
import timl.task.Events;
import timl.task.Task;

public class FileParser {
    protected static final char TODO_SYMBOL = 't';
    protected static final char DEADLINE_SYMBOL = 'd';
    protected static final char EVENT_SYMBOL = 'e';
    protected static final char MARKED = 'X';
    protected static final String SEPARATOR = " ";

    public static Task readTask(String taskData) throws TimException {
        char taskSymbol = taskData.charAt(0);
        boolean isMarked = taskData.charAt(2) == MARKED;
        String taskInput = taskData.substring(4);

        Task task;
        switch (taskSymbol) {
            case TODO_SYMBOL:
                task = new Todo(taskInput);
                break;
            case DEADLINE_SYMBOL:
                String[] deadlineData = splitData(taskInput);
                task = new Deadline(deadlineData[0], deadlineData[1]);
                break;
            case EVENT_SYMBOL:
                String[] eventData = splitData(taskInput);
                task = new Events(eventData[0], eventData[1], eventData[2]);
                break;
            default:
                throw new TimException();
        }

        task.setMarked(isMarked);
        return task;
    }

    protected static String[] splitData(String data) {
        return data.split(SEPARATOR);
    }

    public static String getTodoData(String taskInfo) {
        String doneSymbol = taskInfo.contains("[X]") ? String.valueOf(MARKED) : "o";
        String taskName = taskInfo.substring(taskInfo.indexOf("] ") + 1).trim();

        return TODO_SYMBOL + SEPARATOR + doneSymbol + SEPARATOR + taskName;
    }

    public static String getDeadlineData(String taskInfo) {
        String doneSymbol = taskInfo.contains("[X]") ? String.valueOf(MARKED) : "o";
        String taskName = taskInfo.substring(taskInfo.indexOf("] ") + 1, taskInfo.indexOf("(by:")).trim();
        String deadlineTime = taskInfo.substring(taskInfo.indexOf("(by: ") + 5, taskInfo.lastIndexOf(")")).trim();

        return DEADLINE_SYMBOL + SEPARATOR + doneSymbol + SEPARATOR + taskName + SEPARATOR + deadlineTime;
    }

    public static String getEventData(String taskInfo) {
        String doneSymbol = taskInfo.contains("[X]") ? String.valueOf(MARKED) : "o";
        String taskName = taskInfo.substring(taskInfo.indexOf("] ") + 1, taskInfo.indexOf("(from: ")).trim();
        String eventStart = taskInfo.substring(taskInfo.indexOf("(from: ") + 7, taskInfo.indexOf(" to: "));
        String eventEnd = taskInfo.substring(taskInfo.indexOf(" to: ") + 5, taskInfo.lastIndexOf(")"));

        return EVENT_SYMBOL + SEPARATOR + doneSymbol + SEPARATOR + taskName + SEPARATOR + eventStart + SEPARATOR + eventEnd;
    }
}