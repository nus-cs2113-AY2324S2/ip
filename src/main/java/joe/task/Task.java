package joe.task;

public abstract class Task {
    protected static final String MARKED_STATUS = "[X]";
    protected static final String UNMARKED_STATUS = "[ ]";
    public abstract TaskType getTaskType();
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskStatus() {
        String statusIcon = isDone ? MARKED_STATUS : UNMARKED_STATUS;
        return statusIcon + " " + taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
