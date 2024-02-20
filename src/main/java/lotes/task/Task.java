package lotes.task;

public class Task {

    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ?
                "[X]" :
                "[ ]"); // returns X if lotes.task isDone is true
    }

    public void setDone(boolean isDoneStatus) {
        isDone = isDoneStatus;
    }
    @Override
    public String toString() {
        return description;
    }
}
