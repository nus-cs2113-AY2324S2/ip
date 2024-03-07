public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
