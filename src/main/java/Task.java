public class Task {
    // Description of the task
    protected String description;
    // Status of the task, true if done, false otherwise
    protected boolean isDone;

    // Constructor to create a new task with a description
    public Task(String description) {
        this.description = description;
        this.isDone = false; // New tasks are not done by default
    }

    // Method to mark the task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Method to mark the task as not done
    public void markAsNotDone() {
        this.isDone = false;
    }

    // Method to get the status icon for the task
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // Return "[X]" or "[ ]" based on task completion
    }

    // Override toString method for task representation
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}