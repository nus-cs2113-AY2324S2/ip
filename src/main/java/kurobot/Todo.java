package kurobot;

/**
 * A type of task that only has task name and no timing related details.
 */
public class Todo extends Task {

    /**
     * Store the name and marking status of the task.
     *
     * @param taskName Name of the task
     * @param isMarked Marked or unmarked
     */
    public Todo(String taskName, boolean isMarked) {
        super("T", taskName, isMarked);
    }
}
