public class Task {
    // Fields to store task information
    protected String description; // Description of the task

    protected boolean isDone; // Flag to indicate if the task is done or not

    public void setStatus(boolean x) {
        this.isDone = x;
    }
    // Constructor to initialize a task with a description
    public Task(String description) {
        this.description = description;
        this.isDone = false; // By default, task is not done
    }

    public boolean isDone() {
        return isDone;
    }

    // Method to get the status icon of the task
    public String getStatusIcon(){
        return (this.isDone() ? "[X] " : "[ ] ");
    }

    public String saveTask() {
        return description;
    }
}
