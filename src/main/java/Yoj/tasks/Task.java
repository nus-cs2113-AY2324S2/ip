package Yoj.tasks;
public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public void markDone() {
        this.isDone = true;
    }
    public void markUndone() {
        this.isDone = false;
    }
    public String isCompleted() {
        return (isDone ? "1" : "0");
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String taskType() {
        return "[ ]";
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}