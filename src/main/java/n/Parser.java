package n;

import n.exceptions.NoTaskTypeException;
import n.task.Type;

import static n.TaskList.taskList;

public class Parser {
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
        };
        return taskType;
    }
    public static Type getTaskType(String taskDescription) {
        char taskTypeSymbol = taskDescription.charAt(4);
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
    public static String textToTaskDescription(String text, Type taskType) {
        String taskDescription = "";
        String task = "";
        switch (taskType) {
            case ToDo:
                taskDescription = text.substring(11);
                break;
            case Deadline:
                int deadlineIndex = taskDescription.indexOf("(by");
                task = taskDescription.substring(11, deadlineIndex);
                String deadline = taskDescription.substring(deadlineIndex + 4, taskDescription.length() - 1).trim();
                taskDescription = task + " /by " + deadline;
                break;
            case Event:
                int fromIndex = text.indexOf("(from:");
                int toIndex = text.indexOf("to:");
                task = text.substring(11, fromIndex).trim();
                String fromTime = text.substring(fromIndex + 6, toIndex);
                String toTime = text.substring(toIndex + 3, text.length() - 1);
                taskDescription = task + " /from " + fromTime + " /to " +toTime;
                break;
        }
        return taskDescription;
    }
}
