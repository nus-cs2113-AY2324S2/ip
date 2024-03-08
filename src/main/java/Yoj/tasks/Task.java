package Yoj.tasks;
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a new Task with the given description. By default, the task is not completed.
     *
     * @param description The description of the task, which is the user input
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markDone() {
        this.isDone = true;
    }
    /**
     * Marks the task as not done by setting the completion status to false.
     */
    public void markUndone() {
        this.isDone = false;
    }
    /**
     * Returns the completion status of the task as a string, "1" if isDone is true, "0" otherwise.
     *
     * @return A string representing the completion status of the task.
     */
    public String isCompleted() {
        return (isDone ? "1" : "0");
    }
    /**
     * Gets a status icon representing the task's completion status. "X" if completed, " " (space) otherwise.
     *
     * @return A string representing the visual status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Returns the type of the task. This method should be overridden in subclasses to provide the specific type.
     *
     * @return A string representing the type of the task.
     */
    public String taskType() {
        return "[ ]";
    }
    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}