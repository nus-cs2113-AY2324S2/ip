public class Task {
    protected String description;
    protected boolean isCompleted;

    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
        taskType = "";
    }
    public String saveTaskRepresentation() {
        // An example of the format: "task|0"
        return description + "|" + (isCompleted ? "1" : "0");
    }

    public static Task loadTaskRepresentation(String fileString) {
        String[] fileEntryIntoParts = fileString.split("\\|");
        String taskType = fileEntryIntoParts[0];
        String description = fileEntryIntoParts[1];
        boolean isCompleted = fileEntryIntoParts[2].equals("1");

        Task task;
        switch (taskType) {
        case "D":
            task = new Deadline(description);
            break;
        case "E":
            task = new Event(description);
            break;
        case "T":
            task = new ToDo(description);
            break;
        default:
            // Handle unknown task type
            task = null;
            break;
        }

        if (task != null && isCompleted) {
            task.markAsCompleted(); // Mark task as done if status is '1'
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

