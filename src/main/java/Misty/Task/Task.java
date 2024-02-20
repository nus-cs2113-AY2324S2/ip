package misty.task;

public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    public boolean getIsDone() {
        return isDone;
    }
    public void setTaskAsDone() {
        isDone = true;
    }
    public void setTaskAsNotDone() {
        isDone = false;
    }
}
