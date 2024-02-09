public class Task {
    protected String description;
    protected boolean isDone;

    // Constructor initializes the task with a description and sets its status to not done.
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Sets the task's done status.
    public void setDone(boolean done) {
        isDone = done;
    }

    // Returns a status icon depending on whether the task is done.
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    // Overrides toString to return the task's status icon and description.
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}