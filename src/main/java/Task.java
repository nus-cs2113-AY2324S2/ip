public class Task {

    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return String.format("[" + getStatusIcon() + "] " + name);
    }
}
