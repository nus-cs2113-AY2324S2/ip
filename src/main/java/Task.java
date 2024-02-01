public class Task {
    private String description;
    private boolean isDone;

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

}
