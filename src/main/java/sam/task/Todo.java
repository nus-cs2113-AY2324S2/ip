package sam.task;

// Represents a todo task, inherits from Task class
public class Todo extends Task {

    // Constructor to create a Todo task with description
    public Todo(String description) {
        super(description); // Call constructor of parent class to set description
        isDone = false; // Initialize task as not done
    }

    // Override toString method to represent Todo task as a string
    @Override
    public String toString() {
        // Return string representation of Todo task with its status and description
        return "[T]" + getStatusIcon() + " " + description;
    }

    // Override saveTask method to format Todo task for saving to file
    @Override
    public String saveTask() {
        // Return formatted string to represent Todo task for saving
        return "T | " + (this.isDone() ? "1" : "0") + " | " + description;
    }
}
