package BobBot.tasks;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
    
    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean getMarkedStatus() {
        return this.isDone;
    }
    
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}

