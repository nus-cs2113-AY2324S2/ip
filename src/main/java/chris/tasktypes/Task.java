package chris.tasktypes;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String saveString() { return description; }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean markTask() {
        isDone = !isDone;
        return isDone;
    }
}
