package chris.tasktypes;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
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

    public String getDescription() {
        return description;
    }

}
