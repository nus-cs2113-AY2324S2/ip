package tasks;

/**
 * Represents a ToDo that has been completed or to be completed.
 */
public class ToDo extends tasks.Task {

    /**
     * Character Symbol which represents the task type
     */
    private String taskType = "T";

    /**
     * Constructs a ToDo object with the given description.
     * @param description Description of the ToDo
     */
   public ToDo(String description){
       super(description);
   }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Converts the ToDo to its string representation.
     * @return The string representation of the ToDo
     */
    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]" + super.toString();
    }
}
