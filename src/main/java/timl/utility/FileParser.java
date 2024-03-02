package timl.utility;

import timl.exceptions.TimException;
import timl.task.Deadline;
import timl.task.Todo;
import timl.task.Events;
import timl.task.Task;

/**
 * This class provides functionalities for parsing data to and from file representations.
 */
public class FileParser {
    protected static final char TODO_SYMBOL = 'T';
    protected static final char DEADLINE_SYMBOL = 'D';
    protected static final char EVENT_SYMBOL = 'E';
    protected static final char MARKED = 'X';
    protected static final String SEPARATOR = "-";

    /**
     * Reads a string of data and process it into a new task
     *
     * @param taskData a line of data to be processed
     * @return task a new task that is to be added to the Array List
     * @throws TimException if the task description is empty
     */
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

    /**
     * Splits string around the SEPERATOR
     *
     * @param data string that is to be split
     * @return an array of strings
     */
    protected static String[] splitData(String data) {
        return data.split(SEPARATOR);
    }

    /**
     * Convert information of a Todo task to text file format
     *
     * @param taskInfo information of Todo task that will be split
     * @return a Todo task in text file format
     */
    public static String convertTodoToFile(String taskInfo) {
        String doneSymbol = taskInfo.contains("[X]") ? String.valueOf(MARKED) : "o";
        String taskName = taskInfo.substring(taskInfo.indexOf("] ") + 1).trim();

        return TODO_SYMBOL + SEPARATOR + doneSymbol + SEPARATOR + taskName;
    }

    /**
     * Convert information of a Deadline task to text file format
     *
     * @param taskInfo information of deadline that will be split
     * @return a deadline in text file format
     */
    public static String convertDeadlineToFile(String taskInfo) {
        String doneSymbol = taskInfo.contains("[X]") ? String.valueOf(MARKED) : "o";
        String taskName = taskInfo.substring(taskInfo.indexOf("] ") + 1, taskInfo.indexOf("(by:")).trim();
        String deadlineTime = taskInfo.substring(taskInfo.indexOf("(by: ") + 5, taskInfo.lastIndexOf(")")).trim();

        return DEADLINE_SYMBOL + SEPARATOR + doneSymbol + SEPARATOR + taskName + SEPARATOR + deadlineTime;
    }

    /**
     * Convert information of an Event task to text file format
     *
     * @param taskInfo information of event that will be split
     * @return an event in text file format
     */
    public static String convertEventToFile(String taskInfo) {
        String doneSymbol = taskInfo.contains("[X]") ? String.valueOf(MARKED) : "o";
        String taskName = taskInfo.substring(taskInfo.indexOf("] ") + 1, taskInfo.indexOf("(from: ")).trim();
        String eventStart = taskInfo.substring(taskInfo.indexOf("(from: ") + 7, taskInfo.indexOf(" to: "));
        String eventEnd = taskInfo.substring(taskInfo.indexOf(" to: ") + 5, taskInfo.lastIndexOf(")"));

        return EVENT_SYMBOL + SEPARATOR + doneSymbol + SEPARATOR + taskName + SEPARATOR + eventStart + SEPARATOR + eventEnd;
    }
}