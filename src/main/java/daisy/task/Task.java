package daisy.task;

/**
 * The task class is the parent class of all task related classes. It sets the basic functionalities needed to create a task.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Creates a task instance. Sets the done status of the task to the default value of being undone.
     * @param taskName the task name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        setUndone();
    }

    /**
     * Sets the done status of the task to being done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Sets the done status of the task to being undone.
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Returns a String representation of the task in the format such that it is suitable for program printouts.
     * @return a String representation of the task suitable for printing
     */
    public String toString() {
        return String.format("[%s] %s", (this.isDone)? "X":" ", this.taskName);
    }

    /**
     * Returns a String representation of the task in the format such that it is suitable for loading and saving.
     * @return a String representation of the task suitable for loading and saving
     */
    public String save() {
        return String.format("%s,%s", (this.isDone)? "1":"0", this.taskName);
    }

    public boolean contains(String keyWord) {
        return taskName.contains(keyWord);
    }

}
