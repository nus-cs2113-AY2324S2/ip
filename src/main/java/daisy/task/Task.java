package daisy.task;

public class Task {
    protected String taskName;
    protected boolean isDone;

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

    public String save() {
        return String.format("%s,%s", (this.isDone)? "1":"0", this.taskName);
    }
}
