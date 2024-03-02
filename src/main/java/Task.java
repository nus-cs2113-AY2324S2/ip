public abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public abstract String toString();
}
