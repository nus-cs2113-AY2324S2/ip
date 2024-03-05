package kyrene.task;

import kyrene.exception.KyreneMissingTimeException;

/**
 * Represents an event task that contains task type as "T", task name,
 * and done status.
 */
public class Todo extends Task{

    public Todo() {
        super(null);
    }

    /**
     * Construct a to-do task with a string that contains task name.
     *
     * @param taskName String that contains task name.
     */
    public Todo(String taskName) {
        super(taskName);
        taskType = "T";
    }

    /**
     * Returns a string representing the to-do task that is used for storage purpose.
     *
     * @return A string representing the to-do task.
     */
    @Override
    public String format() { return String.format("%b todo %s\n", isDone, taskName);}

}
