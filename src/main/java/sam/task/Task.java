package sam.task;

// Represents a generic task
public class Task {
    // Fields to store task information
    protected String description; // Description of the task
    protected boolean isDone; // Flag to indicate if the task is done or not

    // Method to set the status of the task
    public void setStatus(boolean x) {
        this.isDone = x;
    }

    // Constructor to initialize a task with a description
    public Task(String description) {
        this.description = description;
        this.isDone = false; // By default, task is not done
    }

    // Method to check if the task is done
    public boolean isDone() {
        return isDone;
    }

    // Method to get the status icon of the task
    public String getStatusIcon(){
        return (this.isDone() ? "[X]" : "[ ]");
    }

    // Method to save the task information
    public String saveTask() {
        return description; // Return task description
    }

    // Method to get the description of the task
    public String getDescription() {
        return this.description; // Return task description
    }
}
