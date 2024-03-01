package mona.task;

/**
 * Represents a task with a description and a completion status. The Task class is a superclass of the
 * Deadline, Event and Todo classes.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructor for Task. Initializes the task with a description and sets its completion
     * status to not done by default.
     *
     * @param description The description of the task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }
    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    /**
     * Returns a symbol representing the completion status of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with "X"
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsNotDone() {
        isDone = false;
    }
}
