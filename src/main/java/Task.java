public class Task {
    private String description;
    private boolean isCompleted;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isCompleted = false; // Tasks start as not completed by default
    }

    // Set the task description
    public void setDescription(String description) {
        this.description = description;
    }

    // Mark the task as completed
    public void completeTask() {
        this.isCompleted = true;
    }

    // Mark the task as not completed (or "uncheck" the task)
    public void uncheckTask() {
        this.isCompleted = false;
    }

    // Get the task description
    public String getDescription() {
        return this.description;
    }

    // Check if the task is completed
    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        return "Task: " + this.description + " - Completed: " + this.isCompleted;
    }
}
