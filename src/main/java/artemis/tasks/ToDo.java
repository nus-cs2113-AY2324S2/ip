package artemis.tasks;

/**
 * Represents a Task to be done
 */
public class ToDo extends Task {
    /**
     * Creates a to do task
     *
     * @param taskName the name of the task to be done
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a formatted string representation of the task to be done
     *
     * @return Formatted string of to do
     */
    @Override
    public String toString() {
        return String.format("[T] [%s] %s", super.getStatusIcon(), super.taskName);
    }
}
