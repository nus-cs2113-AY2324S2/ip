package Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String printFileFormat() {
        return this.getClass().getSimpleName().charAt(0) + " | " + ((isDone)? 1 : 0) + " | " + description;
    }
}