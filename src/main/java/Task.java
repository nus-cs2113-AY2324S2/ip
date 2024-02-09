public class Task {
    protected String description;
    protected boolean isDone;

    protected String typeOfTask;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        typeOfTask = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsCompleted() {
        isDone = true;
    }

    public void markAsNotCompleted() {
        isDone = false;
    }

}
