package anonbot.task;

public class Task {
    public enum TaskType {
        TODO, DEADLINE, EVENT,
        INVALID
    }

    private String taskDescription;
    private int taskNumber;
    private TaskType taskType;
    private boolean isTaskDone;

    /**
     * Creates a new Task with an associated task number and task type.
     *
     * @param taskDescription Description of the task.
     * @param taskNumber The task number this task is tagged to.
     * @param taskType The type of task.
     */
    public Task(String taskDescription, int taskNumber, TaskType taskType) {
        setTaskDescription(taskDescription);
        setTaskNumber(taskNumber);
        setTaskType(taskType);
        setTaskStatus(false);
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public boolean isTaskDone() {
        return isTaskDone;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Sets the task description.
     *
     * @param taskDescription Description of the task.
     */
    protected void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Give the newly created task an associated task number.
     */
    private void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Sets the status of the associated task.
     *
     * @param isTaskDone Whether the task has been marked completed.
     */
    public void setTaskStatus(boolean isTaskDone) {
        this.isTaskDone = isTaskDone;
    }

    private void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    private char getCharRepresentationOfTaskType() {
        switch (getTaskType()) {
        case TODO:
            return 'T';
        case EVENT:
            return 'E';
        case DEADLINE:
            return 'D';
        case INVALID:
            return 'I';
        default:
            return '?';
        }
    }

    public void printTask() {
        System.out.format("%d.[%c][%c] %s" + System.lineSeparator(),
                getTaskNumber(),
                getCharRepresentationOfTaskType(),
                isTaskDone() ? 'X' : ' ',
                getTaskDescription());
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        setTaskStatus(true);
        System.out.println("Cool, this task is now done:");
        printTask();
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        setTaskStatus(false);
        System.out.println("Oh okay, this task is now marked as undone:");
        printTask();
    }
}
