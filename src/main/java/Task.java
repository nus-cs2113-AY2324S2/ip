public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task() {
        setDone(false);
    }

    public Task(String taskName) {
        setTaskName(taskName.trim());
        setDone(false);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getTaskName();
    }
}
