/**
 * Todo task class
 *
 * @param taskType Specify what type of task it is.
 */
public class Todo extends Task{
    protected String taskType;
    public Todo (String task, String taskType) {
        super(task);
        this.taskType = taskType;
    }

    /** Returns the task type */
    @Override
    public String getTaskType() {
        return taskType;
    }
}
