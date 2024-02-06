public class Task {
    protected String description; //Holds the textual description of the task.
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; //Initialize the status of the new task to not done.
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // Replace tasks that are done with [X].
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
