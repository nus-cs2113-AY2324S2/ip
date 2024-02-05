public class Task {
    private String taskDescription;
    private Boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public Boolean getTaskStatus() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }
}
