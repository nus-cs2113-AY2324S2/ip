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

    public String getStatusIcon() {
        return (isDone ?
                "[X]" :
                "[ ]"); // returns X if task isDone is true
    }

    public void setDone(boolean isDoneStatus) {
        isDone = isDoneStatus;
    }
}
