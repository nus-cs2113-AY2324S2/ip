public class Task {
    protected String description;
    protected boolean isDone;
    protected int sequenceNumber = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public int getIndex() {
        return sequenceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void isDone(Boolean status) {
        this.isDone = status;
    }

}

