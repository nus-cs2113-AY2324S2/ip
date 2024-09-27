package artemis.tasks;

/**
 * Represents a task with a name and completion status.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Creates a task with the given task name
     *
     * @param taskName The name of the task to be created
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the assigned task name
     *
     * @return String of task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns an icon of the status of the task
     *
     * @return An X if the task is completed, empty space if incomplete
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the task as complete or incomplete
     *
     * @param done True if the task is done, false if incomplete
     */
    public void markAsDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns the boolean status of the task
     *
     * @return True if the task is done, false if incomplete
     */
    public boolean getIsDone() {
        return isDone;
    }
}
