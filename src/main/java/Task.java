import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileString() {
        // Implement how you want to represent the task as a string for file storage
        // For example, you can concatenate the description and status with a delimiter
        return description + "|" + (isDone ? "1" : "0"); // Example format: "description|status"
    }

    //@@author PradeepM
    public static Task fromString(String fileString) {
        String[] parts = fileString.split("\\|");

        // Check if the parts array has enough elements
        if (parts.length < 3) { // Added this line
            // Handle the case where the input string format is incorrect
            return null;
        }

        String taskType = parts[0]; // Extract task type
        String description = parts[1];
        boolean isDone = parts[2].equals("1"); // Extract status

        Task task;
        switch (taskType) {
        case "D":
            // Check if the parts array has enough elements for a Deadline task
            if (parts.length >= 4) { // Added this line
                task = new Deadline(description, parts[3]); // Create Deadline task
            } else {
                task = null;
            }
            break;
        case "E":
            // Check if the parts array has enough elements for an Event task
            if (parts.length >= 5) { // Added this line
                task = new Event(description, parts[3], parts[4]); // Create Event task
            } else {
                task = null;
            }
            break;
        case "T":
            task = new Todo(description); // Create Todo task
            break;
        default:
            // Handle unknown task type
            task = null;
            break;
        }

        if (task != null && isDone) {
            task.markAsDone(); // Mark task as done if status is '1'
        }

        return task;
    }
    //@@author PradeepM
}



