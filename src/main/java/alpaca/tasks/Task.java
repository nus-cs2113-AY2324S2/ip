package alpaca.tasks;
abstract public class Task {
    protected final String description;
    protected boolean isDone;

    protected int IntIsDone; //for saving

    Task(String description) {
        this.isDone = false;
        this.description = description;
        this.IntIsDone = 0;
    }

    public void mark() {
        this.isDone = true;
        this.IntIsDone = 1;
    }

    public void unmark() {
        this.isDone = false;
        this.IntIsDone = 0;
    }

    public String toString() {
        if (isDone) {
            return  "[X] " + description;
        }
        return  "[ ] " + description;
    }

    abstract public String save();
}