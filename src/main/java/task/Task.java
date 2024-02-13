package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String identity;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        identity = "[ ]";
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return description;
    }

    public void changeStatus(boolean status) {
        isDone = status;
    }

    public String getIdentity() {
        return identity;
    }
}
