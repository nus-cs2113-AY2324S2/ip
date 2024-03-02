package Event;

public class Task {
    static String BREAK_LINE = "____________________________________________________________";
    public String description; //Holds the textual description of the task.
    public boolean isDone;
    protected String eventType;

    public Task(String description) {

        this.description = description;
        this.isDone = false; //Initialize the status of the new task to not done.
        this.eventType = null;

    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTasksIcon() {
        return eventType;
    }

    public String getTasksStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return this.getTasksIcon() + this.getTasksStatus() + " " + this.description;
    }

    public String toStorageString() {
        return "";
    }
}

