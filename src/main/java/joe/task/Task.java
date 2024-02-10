package joe.task;

public abstract class Task {
    protected static final String MARKED_STATUS = "[X]";
    protected static final String UNMARKED_STATUS = "[ ]";
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

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
