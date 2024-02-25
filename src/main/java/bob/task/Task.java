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
     * Task Name getter method.
     *
     * @return Task Name String.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Completion Status getter method.
     *
     * @return String value representing completion status. "1" for True, "0" for False.
     */
    public String getCompletionStatus() {
        return (this.isCompleted) ? "1" : "0";
    }

    /**
     * Marks a Task as completed.
     *
     * @return New instance of the completed Task.
     */
    abstract public Task markTaskAsComplete();

    /**
     * Marks a Task as incomplete.
     *
     * @return New instance of the incomplete Task.
     */
    abstract public Task markTaskAsIncomplete();

    @Override
    public String toString() {
        return (isCompleted) ? String.format("[X] %s", this.taskName) : String.format("[ ] %s", this.taskName);
    }
}
