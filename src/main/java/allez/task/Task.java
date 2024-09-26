package allez.task;

/**
 * An abstract class that is inherited by ToDo, Deadline and Event.
 * Stores description of task and whether the task is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String of symbols to show whether the task has been marked as done.
     * [X] for done, [ ] for not done.
     *
     * @return a String that symbolises if a task has been done
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the status and description of the task as a string.
     *
     * @return status and description of the task
     */
    public String toString() {
        return this.getStatusIcon() +
                " " + this.getDescription();
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of task
     */
    public String getDescription() {
        return description;
    }

}
