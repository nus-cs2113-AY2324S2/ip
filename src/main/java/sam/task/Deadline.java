package sam.task;

// Represents a task with a deadline, inherits from Task class
public class Deadline extends Task {
    // Deadline time
    protected String by;

    // Constructor to create a Deadline task with description and deadline
    public Deadline(String description, String by) {
        super(description); // Call constructor of parent class to set description
        this.isDone = false; // Initialize task as not done
        this.by = by; // Set the deadline time
    }

    // Setter method to update the deadline time
    public void setBy(String by) {
        this.by = by;
    }

    // Getter method to retrieve the deadline time
    public String getBy() {
        return by;
    }

    // Override toString method to represent Deadline task as a string
    @Override
    public String toString() {
        // Return string representation of Deadline task with its status, description, and deadline
        return "[D]" + getStatusIcon() + " " + description + " (by: " + by + ")";
    }

    // Override saveTask method to format Deadline task for saving to file
    @Override
    public String saveTask() {
        // Return formatted string to represent Deadline task for saving
        return "D | " + (this.isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
