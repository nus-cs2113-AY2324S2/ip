public class Task {
    private final String taskName;
    private final int taskId;
    private final boolean isCompleted;

    private Task(String taskName, int taskId, boolean isCompleted) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.isCompleted = isCompleted;
    }

    public Task(String taskName, int taskId) {
        this(taskName, taskId, false);
    }

    public String getTaskName() {
        return taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Task completeTask() {
        return new Task(this.taskName, this.taskId, true);
    }
}
