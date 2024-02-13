package utils;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public void printTask() {
        if (this.isDone) {
            System.out.println("[X] " + this.description);
        } else {
            System.out.println("[ ] " + this.description);
        }
    }
}