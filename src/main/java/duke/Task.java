package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskId;

    public Task(String description) {
        this.description = description;
        isDone  = false;
    }

    public void doneIsFalse() {
        isDone = false;
    }

    public void doneIsTrue() {
        isDone = true;
    }

    public String getDoneStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return taskId + this.getDoneStatus() + " " + description;
    }

}
