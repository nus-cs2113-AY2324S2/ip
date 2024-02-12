package gary.task;

public class Task {
    private String taskDescription;
    private Boolean isDone;
    protected TaskType taskType;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.taskType = null;
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

    public void printAdd(int todosCount) {
        return;
    }

    public void printTask(int todoCount) {
        return;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }
}
