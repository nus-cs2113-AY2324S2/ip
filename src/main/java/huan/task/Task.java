package huan.task;

/**
 * Class for a regular task
 */
public class Task {
    private String name;
    private Boolean isDone;

    private int taskType;

    /**
     * Set method for task name
     * @param name the task name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set method for isDone
     * @param isDone whether the task is marked as finished
     */
    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Set method for task type
     * @param taskType the type of task, 1 for TodoTask, 2 for EventTask, 3 for Deadline Task. Used for recasting
     */
    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    /**
     * Get method for task name
     * @return the task name
     */
    public String getName() {
        return name;
    }

    /**
     * Get method for isDone
     * @return whether the task is marked as finished
     */
    public Boolean getIsDone() {
        return isDone;
    }

    /**
     * Get method for taskType
     * @return the type of task, 1 for TodoTask, 2 for EventTask, 3 for Deadline Task.
     */
    public int getTaskType() {
        return taskType;
    }

    /**
     * Method for printing a task. Will be overridden by other classes
     */
    public void printTask() {
        System.out.println("[" + (isDone ? "X" : " ") + "] " + name);
    }

    /**
     * Method for writing the line. Will be overridden by other classes
     * @return nothing
     */
    public String writeLine() {
        return null;
    }

    /**
     * Default Constructor class for Task
     */
    public Task() {
        setName("task");
        setIsDone(false);
        taskType = 0;
    }

    /**
     * Constructor class for Task
     * @param name the task name
     * @param isDone whether the task will be marked as finished
     */
    public Task(String name, Boolean isDone) {
        setName(name);
        setIsDone(isDone);
    }
}
