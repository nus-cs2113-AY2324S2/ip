public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone () {
        this.isDone = true;
        System.out.println("[" + getStatusIcon() + "]" + this.description);
    }

    public void markAsUndone () {
        this.isDone = false;
        System.out.println("[" + getStatusIcon() + "]" + this.description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //...
}