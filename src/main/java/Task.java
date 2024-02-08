public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }
    public void setDone() {
        isDone = true;
    }
    public void setUndone() {
        isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", (this.isDone)? "X":" ", this.taskName);
    }
}
