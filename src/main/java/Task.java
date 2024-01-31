public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public String getName() {
        return name;
    }
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }
    public void mark() {
        isDone = true;
    }
    public void unmark() {
        isDone = false;
    }
}
