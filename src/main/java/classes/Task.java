package classes;

/**
 * This class represents a task
 */
public class Task {
    protected String description;
    protected String time;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.time = null;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task
     * @return the status of the task
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the type of the task
     * @return the type of the task
     */
    public String getTaskType() {
        return "Task";
    }

    /**
     * Returns the time of the task
     * @return the time of the task
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Sets the time of the task
     * @param time the time of the task
     * @return the time of the task
     */
    public String setTime(String time) {
        return this.time = time;
    }

    /**
     * Prints the task
     */
    public void printTask() {
        if (this.isDone) {
            System.out.println("[X] " + this.description);
        } else {
            System.out.println("[ ] " + this.description);
        }
    }
}