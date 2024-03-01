public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with a description and completeness.
     * Task is a subclassed by the to-do, deadline and event classes.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns X when the task is completed.
     * If task is not complete it returns a single space.
     *
     * @return String that represents the completeness of a task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }
}
