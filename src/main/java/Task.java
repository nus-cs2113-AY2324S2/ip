public class Task {
    protected String description;
    protected boolean isCompleted;

    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
        taskType = "";
    }
    public String toFileString() {
        // Implement how you want to represent the task as a string for file storage
        // For example, you can concatenate the description and status with a delimiter
        return description + "|" + (isCompleted ? "1" : "0"); // Example format: "description|status"
    }

    /**
     * Parses a string from a file into a Task object.
     *
     * @param fileString The string read from the file representing a task.
     * @return The Task object parsed from the string.
     */
    public static Task fromString(String fileString) {
        // Implement how you want to parse the string from the file into a Task object
        // For example, split the string by the delimiter and create a Task object from the parts
        String[] parts = fileString.split("\\|");
        String description = parts[0];
        boolean isDone = parts[1].equals("1"); // Example format: "description|status"
        Task task = new Task(description);
        if (isDone) {
            task.markAsCompleted();
        }
        return task;
    }


    public String getStatusIcon() {
        return (isCompleted ? "✔" : " "); // mark done task with X
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public void markAsNotCompleted() {
        isCompleted = false;
    }


}

