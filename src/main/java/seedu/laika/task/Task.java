package seedu.laika.task;
public class Task {

    protected String description;

    protected boolean isDone;

    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }
    public String getDescription() {
        return description;
    }
    public String getIsDoneValue() {
        return (isDone ? "1" : "0");
    }

    public String toString() {
        return  getStatusIcon() + description;
    }

    public void markAsDone(){
        isDone = true;
    }

    public void markAsUndone(){
        isDone = false;
    }
}