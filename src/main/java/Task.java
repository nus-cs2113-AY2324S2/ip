public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeOfTask;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        typeOfTask = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsCompleted() {
        isDone = true;
    }

    public void markAsNotCompleted() {
        isDone = false;
    }

    public String toFileString() {
        return description + "|" + (isDone ? "1" : "0"); // Example format: "description|status"
    }

    public static Task fromString(String fileString) {
        String[] parts = fileString.split("\\|");
        String taskType = parts[0]; // Extract task type
        String description = parts[1];
        boolean isDone = parts[2].equals("1"); // Extract status

        Task task;
        switch (taskType) {
            case "D":
                task = new Deadline(description); // Create Deadline task
                break;
            case "E":
                task = new Event(description); // Create Event task
                break;
            case "T":
                task = new ToDo(description); // Create Todo task
                break;
            default:
                // Handle unknown task type
                task = null;
                break;
        }

        if (task != null && isDone) {
            task.markAsCompleted(); // Mark task as done if status is '1'
        }

        return task;
    }

}
