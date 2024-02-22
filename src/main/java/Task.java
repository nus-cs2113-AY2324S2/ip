public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public String toFileFormat() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

}
