package sam.task;

// Represents an event task, inherits from Task class
public class Event extends Task {
    // Attributes to store start and end time of the event
    private String start;
    private String end;

    // Constructor to create an Event task with description, start time, and end time
    public Event(String description, String start, String end) {
        super(description); // Call constructor of parent class to set description
        this.start = start; // Set the start time of the event
        this.end = end; // Set the end time of the event
    }

    // Override toString method to represent Event task as a string
    @Override
    public String toString() {
        // Return string representation of Event task with its status, description, start time, and end time
        return "[E]" + getStatusIcon() + " " + description + " (" + start + " to " + end + ")";
    }

    // Override saveTask method to format Event task for saving to file
    @Override
    public String saveTask() {
        // Return formatted string to represent Event task for saving
        return "E | " + (this.isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
    }
}
