/**
 * Parser takes in user inputs/inputs from the .txt file
 * and makes sense of it so that the corresponding action
 * can be taken.
 *
 * @author  anneleong
 * @version 1.0
 * @since   2024-03-07
 */

package n;

import n.exceptions.NoTaskTypeException;
import n.task.Type;
public class Parser {
    public static final int BEGIN_INDEX = 11;
    public static final int FROM_LENGTH = 6;
    public static final int TO_LENGTH = 3;
    public static final int BY_LENGTH = 4;

    /**
     * Determines the type of task based on the provided task description.
     *
     * @param taskDescription The description of the task.
     * @return The Type of task (Event, Deadline, ToDo).
     * @throws NoTaskTypeException If the task type is not specified correctly.
     */
    public static Type filterTask(String taskDescription) throws NoTaskTypeException {
        Type taskType;
        switch (taskDescription.split(" ")[0]) {
            case "event":
                taskType = Type.Event;
                break;
            case "deadline":
                taskType = Type.Deadline;
                break;
            case "todo":
                taskType = Type.ToDo;
                break;
            default:
                throw new NoTaskTypeException();
        }
        return taskType;
    }
    /**
     * Gets the Type of task based on its task description
     * as text in the .txt saved file.
     *
     * @param taskDescription The description of the task.
     * @return The Type of the task (ToDo, Deadline, Event)
     * @see Storage#loadTaskList()
     */
    public static Type getTaskType(String taskDescription) {
        char taskTypeSymbol = taskDescription.charAt(BY_LENGTH);
        Type taskType = Type.ToDo;
        switch (taskTypeSymbol) {
            case ('T'):
                taskType = Type.ToDo;
                break;
            case ('D'):
                taskType = Type.Deadline;
                break;
            case('E'):
                taskType = Type.Event;
                break;
        }
        return taskType;
    }
    /**
     * Converts a given task as a text from the .txt saved
     * file into a formatted task description based on the
     * specified Type of task.
     *
     * @param text      The input text containing task-related information.
     * @param taskType  The type of the task (ToDo, Deadline, Event).
     * @return The formatted task description.
     * @see Storage#loadTaskList()
     */
    public static String textToTaskDescription(String text, Type taskType) {
        String taskDescription = "";
        String task;
        switch (taskType) {
            case ToDo:
                taskDescription = text.substring(BEGIN_INDEX);
                break;
            case Deadline:
                int deadlineIndex = taskDescription.indexOf("(by");
                task = taskDescription.substring(BEGIN_INDEX, deadlineIndex);
                String deadline = taskDescription.substring(deadlineIndex + BY_LENGTH, taskDescription.length() - 1).trim();
                taskDescription = task + " /by " + deadline;
                break;
            case Event:
                int fromIndex = text.indexOf("(from:");
                int toIndex = text.indexOf("to:");
                task = text.substring(BEGIN_INDEX, fromIndex).trim();
                String fromTime = text.substring(fromIndex + FROM_LENGTH, toIndex);
                String toTime = text.substring(toIndex + TO_LENGTH, text.length() - 1);
                taskDescription = task + " /from " + fromTime + " /to " +toTime;
                break;
        }
        return taskDescription;
    }
}
