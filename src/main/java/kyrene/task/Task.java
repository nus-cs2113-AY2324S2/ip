package kyrene.task;

/**
 * Represents a task that contains task type as null, task name,
 * and done status.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String doneSymbol;
    protected String taskType;

    public Task() {
        this(null);
    }

    /**
     * Construct a to-do task with a string that contains task name.
     *
     * @param taskName String that contains task name.
     */
    public Task(String taskName) {
        if(taskName == null) {
            return;
        }
        setTaskName(taskName);
        setDone(false);
        taskType = null;
    }

    /**
     * Returns a string representing task name.
     *
     * @return String representing task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the done status of the task, true for done, otherwise not done.
     *
     * @return Done status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representing start date and time of the task.
     *
     * @param taskName String representing the task name.
     */
    protected void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Set the done status of the task, true for done, otherwise not done.
     *
     * @param isDone Done status of the task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
        if(isDone){
            doneSymbol = "√";
        }
        else{
            doneSymbol = " ";
        }
    }

    /**
     * Returns an upper-case character representing the task type.
     *
     * @return Task type.
     */
    public String getTaskType(){
        return taskType;
    }

    /**
     * Returns a string representing the task that is used for output purpose.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString(){
        return String.format("[%s][%s] %s", taskType, doneSymbol, taskName);
    }

    /**
     * Returns a string representing the task that is used for storage purpose.
     *
     * @return A string representing the task.
     */
    public String format() { return String.format("%b task %s\n", isDone, taskName);}
}
