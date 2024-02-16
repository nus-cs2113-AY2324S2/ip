package Sinep;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;// Description of the task
        this.isDone = false;//Completion status of the task(True: Done, False: Undone)
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");// mark tasks that are done with X, else empty space
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String toString() {
        return getStatusIcon() + " " + description; //combine the status and task description for easier listing
    }
}
