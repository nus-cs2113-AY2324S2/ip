public class Task {
    protected String description;
    protected boolean isDone;

    public String toFileFormat() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
