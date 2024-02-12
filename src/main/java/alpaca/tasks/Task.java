package alpaca.tasks;
public class Task {
    protected final String description;
    protected boolean isDone;

    Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        if (isDone) {
            return  "[X] " + description;
        }
        return  "[ ] " + description;
    }
}