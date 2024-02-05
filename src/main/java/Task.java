public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + description;
    }
}