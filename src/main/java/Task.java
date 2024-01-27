public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        System.out.println("Good job.");
        isDone = true;
        System.out.println("[X] " + description);
    }

    public void markAsUndone() {
        System.out.println("Lazy. Just simply lazy.");
        isDone = false;
        System.out.println("[ ] " + description);
    }
}
