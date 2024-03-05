/**
 * Represents a general {@code Task} for the Jarvas bot.
 */
public abstract class Task {
    protected String label;
    protected boolean isCompleted;

    /**
     * Constructs a new {@code Task} object.
     *
     * @param input Label of the task.
     */
    public Task(String input) {
        this.label = input;
        this.isCompleted = false;
    }

    /**
     * Returns a symbol representing the completion status of a task.
     *
     * @return A string representing completion status of a {@code Task} object.
     */
    public String getStatusIcon() {
        return (isCompleted ? "X" : " "); // mark done task with X
    }


    /**
     * Marks the task as done or not done based on {@code isCompleted} parameter.
     * If {@code isCompleted} is {@code true}, task is completed.
     *
     * @param value A boolean variable representing completion status of task.
     */
    public void setCompleted(boolean value) {
        this.isCompleted = value;
    }
    /**
     * Retrieves a string representing the task type.
     *
     * @return A string 'task' representing {@code Task} tasks.
     */
    public abstract String getType();
    /**
     * Retrieves the label of a task.
     *
     * @return A string representing the label of a {@code Task} object.
     */
    public String getLabel(){
        return label;
    };
    /**
     * Retrieves a string representing the time range assigned to a {@code Task} object.
     *
     * @return A string representing any time ranges associated with the {@code Task} object.
     */
    public abstract String getRange();

    /**
     * Returns a formatted string representing the {@code Task} object.
     *
     * @return A string representing a {@code Task} object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + label;
    }
}
