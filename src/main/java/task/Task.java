package task;

public class Task {
    public enum TaskType {
        TODO, DEADLINE, EVENT,
        INVALID
    }

    private String taskDescription;
    private int taskNumber;
    private boolean isTaskDone;
    private TaskType taskType;

    /**
     * Creates a new Task.
     * An associated task number is assigned to the task.
     * The created Task is also added to the taskList (at the end) for tracking.
     *
     * @param taskDescription Description of the task.
     */
    public Task(String taskDescription, int taskNumber) {
        setTaskDescription(taskDescription);
        setTaskNumber(taskNumber);
        setTaskStatus(false);
        setTaskType(TaskType.INVALID);
    }

    /**
     * Gets the task description.
     *
     * @return The task description.
     */
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

    public char getCharRepresentationOfTaskType(){
        switch (getTaskType()){
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

    /**
     * Sets the task description.
     *
     * @param taskDescription Description of the task.
     */
    private void setTaskDescription(String taskDescription) {
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
    private void setTaskStatus(boolean isTaskDone) {
        this.isTaskDone = isTaskDone;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
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
