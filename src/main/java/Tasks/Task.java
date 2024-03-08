package Tasks;

/**
 * Represents a Task object that can be used as a parent class for deadline, event and todo type classes.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected static int counter = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static int getNoOfTask() {
        return counter;
    }

    public static void setNoOfTask(int numberOfTasks) {
        counter = numberOfTasks;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
