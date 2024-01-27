public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskStatus() {
        String statusIcon = isDone ? "[X]" : "[ ]";
        return statusIcon + " " + taskName;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
