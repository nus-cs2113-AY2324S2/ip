abstract public class Task {
    protected final String taskName;
    protected final int taskId;
    protected final boolean isCompleted;

    protected Task(String taskName, int taskId, boolean isCompleted) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.isCompleted = isCompleted;
    }

    public int getTaskId() {
        return taskId;
    }

    abstract public Task markTaskAsComplete();

    abstract public Task markTaskAsIncomplete();

    @Override
    public String toString() {
        return (isCompleted) ? String.format("[X] %s", this.taskName) : String.format("[ ] %s", this.taskName);
    }
}
