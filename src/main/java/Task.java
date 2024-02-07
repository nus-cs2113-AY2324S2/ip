public class Task {
    protected String description;
    protected boolean isCompleted;

    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
        taskType = "";
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

