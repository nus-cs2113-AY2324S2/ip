package anonbot.misc;

import anonbot.exception.ExportDataException;
import anonbot.task.Deadline;
import anonbot.task.Event;
import anonbot.task.Task;

/**
 * Parses the different tasks in the tasklist into a savable format.
 */
public class ExportParser {
    /**
     * Converts the corresponding task into a representable string.
     * The string format follows closely that of the command syntax for easier parsing when reading.
     *
     * @param task The task to be exported.
     * @return The corresponding string-equivalent representation for that particular task.
     * @throws ExportDataException If a task other than todo, deadline and event are exported.
     */
    public static String convertTaskToCommandlineFormat(Task task) throws ExportDataException {
        String taskDescription = task.getTaskDescription();
        String taskNumberString = String.valueOf(task.getTaskNumber());
        String isTaskDoneString = task.isTaskDone() ? "Y" : "N";

        switch (task.getTaskType()) {
        case TODO:
            return String.format("todo %s %s %s", isTaskDoneString, taskNumberString, taskDescription);
        case DEADLINE:
            Deadline deadlineTask = (Deadline) task;
            return String.format("deadline %s %s %s /by %s",
                    isTaskDoneString, taskNumberString, taskDescription, deadlineTask.getBy());
        case EVENT:
            Event eventTask = (Event) task;
            return String.format("event %s %s %s /from %s /to %s",
                    isTaskDoneString, taskNumberString, taskDescription, eventTask.getFrom(), eventTask.getTo());
        default:
            throw new ExportDataException("Unknown Task Type. Not saving this task.");
        }
    }
}
