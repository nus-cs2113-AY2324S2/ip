public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }

    /**
     * Converts the task to a string representation that can be written to a file.
     *
     * @return A string representation of the task for file storage.
     */
    public String toFileString() {
        // Implement how you want to represent the task as a string for file storage
        // For example, you can concatenate the description and status with a delimiter
        return description + "|" + (isDone ? "1" : "0"); // Example format: "description|status"
    }

    /**
     * Parses a string from a file into a Task object.
     *
     * @param fileString The string read from the file representing a task.
     * @return The Task object parsed from the string.
     */
    public static Task fromString(String fileString) {
        String[] parts = fileString.split("\\|");
        String taskType = parts[0]; // Extract task type
        String description = parts[1];
        boolean isDone = parts[2].equals("1"); // Extract status

        Task task;
        switch (taskType) {
        case "D":
            task = new Deadline(description, parts[3]); // Create Deadline task
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]); // Create Event task
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
            task.markTask(); // Mark task as done if status is '1'
        }

        return task;
    }

}
