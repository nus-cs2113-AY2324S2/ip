public class Task {
    // Fields to store task information
    protected String description; // Description of the task
    protected boolean isDone; // Flag to indicate if the task is done

    // Constructor to initialize a task with a description
    public Task(String description) {
        this.description = description;
        this.isDone = false; // By default, task is not done
    }

    // Method to get the status icon of the task
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // Return "X" if the task is done, otherwise return " "
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    // Method to set the status of the task
    // Takes an integer parameter: 0 for not done, 1 for done
    public void setStatus(int x) {
        this.isDone = (x == 0 ? false : true); // Set isDone based on the value of x
    }
}
