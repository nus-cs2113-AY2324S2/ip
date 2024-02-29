package joe.task;

public abstract class Task {
    protected static final String MARKED_STATUS = "[X]";
    protected static final String UNMARKED_STATUS = "[ ]";
    public abstract TaskType getTaskType();
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns a full description of the task
     *
     * @return a String of the task description
     */
    public String getTaskStatus() {
        String statusIcon = isDone ? MARKED_STATUS : UNMARKED_STATUS;
        return statusIcon + " " + taskName;
    }

    /**
     * Returns the task name
     *
     * @return a String of the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the boolean parameter isDone of the task accordingly
     *
     * @param isDone a boolean to set isDone of the task
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
