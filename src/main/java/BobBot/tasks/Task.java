package BobBot.tasks;

/**
 * Implements a parent task that stores the details of any type of task.
 * 
 * <p>Tasks can be marked as done or undone and have a description.</p>
 * 
 * @author NicholasTanYY
 * @since January 2024
 * @version 1.0
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description.
     * 
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
    
    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean getMarkedStatus() {
        return this.isDone;
    }
    
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}

