package Kowalski.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsNotDone(){

        this.isDone = false;
    }
    public void markAsDone(){

        this.isDone = true;
    }

    public String textFileInputString() {
        return String.format("%s | %s",
                isDone? "X" : "0",
                getDescription().trim());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
