/**
 * The Task class represents a task in the task list.
 * It is an abstract class that provides common functionality for different types of tasks.
 * Each task has a description and a status indicating whether it is done or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public abstract String toFileString();

    // Method to update the status icon based on task completion
    public void updateStatusIcon() {
        // If the task is done, mark it with [X], otherwise mark it with [ ]
        String statusIcon = (isDone ? "[X]" : "[ ]");
        // Update the description with the new status icon
        this.description = statusIcon + " " + description.substring(4); // Remove existing status icon before updating
    }

    @Override
    public String toString() {
        return description;
    }
}
