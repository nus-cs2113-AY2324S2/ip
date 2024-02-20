public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String printFileFormat() {
        return this.getClass().getSimpleName().charAt(0) + " | " + ((isDone)? 1 : 0) + " | " + description;
    }
}