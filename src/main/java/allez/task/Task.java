package allez.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    private static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks +=1;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String toString() {
        return this.getStatusIcon() +
                " " + this.getDescription();
    }

    public void markDone() {
        this.isDone = true;
    }
    public void unmarkDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
