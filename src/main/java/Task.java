public class Task {
    private String taskName;
    private boolean isDone;
    private TaskType taskType;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        String type;
        switch (taskType) {
        case TODO:
            type = "[T]";
            break;
        case DEADLINE:
            type = "[D]";
            break;
        case EVENT:
            type = "[E]";
            break;
        default:
            type = "[INVALID]";
        }
        return type + " " + status + " " + taskName;
    }
}
