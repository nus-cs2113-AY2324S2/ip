package anonbot.misc;

import anonbot.exception.InvalidTaskException;
import anonbot.task.Deadline;
import anonbot.task.Event;
import anonbot.task.Task;

public class ExportParser {
    public static String convertTaskToCommandlineFormat(Task task) throws InvalidTaskException {
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
            throw new InvalidTaskException("Unknown Task Type. Not saving this task.");
        }
    }
}
