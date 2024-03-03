package bob.task;

/**
 * Abstract Task class.
 */
abstract public class Task {
    protected final String taskName;
    protected final boolean isCompleted;

    protected Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the name of the current Task.
     *
     * @return Task Name String.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the Completion Status of the Task.
     * Completion Status is "1" if the Task is completed.
     * Else, Completion Status is "0".
     *
     * @return String value representing completion status.
     */
    public String getCompletionStatus() {
        return (isCompleted) ? "1" : "0";
    }

    /**
     * Marks a Task as completed, and returns a new instance of the completed Task.
     *
     * @return New instance of the completed Task.
     */
    abstract public Task markTaskAsComplete();

    /**
     * Marks a Task as incomplete, and returns a new instance of the incomplete Task.
     *
     * @return New instance of the incomplete Task.
     */
    abstract public Task markTaskAsIncomplete();

    @Override
    public String toString() {
        return (isCompleted) ? String.format("[X] %s", taskName) : String.format("[ ] %s", taskName);
    }
}
