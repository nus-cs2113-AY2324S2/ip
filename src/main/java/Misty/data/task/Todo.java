package misty.data.task;

/**
 * Create todo tasks.
 */
public class Todo extends Task {
    /**
     * Constructs todo object.
     *
     * @param taskName Name of todo task.
     */
    public Todo (String taskName) {
        super(taskName);
    }

    /**
     * Returns string of how todo task should be printed to screen.
     *
     * @return String to be printed to screen.
     */
    @Override
    public String toString() {
        return "[T]" + getStatus() + " " + getTaskName();
    }
}