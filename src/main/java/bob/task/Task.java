package bob.task;

abstract public class Task {
    protected final String taskName;
    protected final boolean isCompleted;

    protected Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    abstract public Task markTaskAsComplete();

    abstract public Task markTaskAsIncomplete();

    @Override
    public String toString() {
        return (isCompleted) ? String.format("[X] %s", this.taskName) : String.format("[ ] %s", this.taskName);
    }
}
