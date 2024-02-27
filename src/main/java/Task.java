public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     *  Returns string specifying completion status of task in array list
     *
     * @return string "[X]" or "[ ]" depending on whether instance member isDone contains true or false
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Returns task description and whether it has been completed or not
     *
     * @return completion status of task and task description as string
     */
    public String toString() {
        return getStatusIcon() + getDescription();
    }


}