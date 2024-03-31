package timl.task;

public abstract class Task {
    protected static final String MARKED = "[X]";
    protected static final String UNMARKED = "[ ]";
    protected boolean isMarked;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Returns description of a task
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string containing the marked status and the description of a task
     *
     * @return string containing marked/unmarked icon and the task description
     */
    public String getStatus() {
        String icon = isMarked ? MARKED : UNMARKED;
        return icon + " " + description;
    }

    /**
     * Sets the mark status of the task to the value of x
     *
     * @param x marked status of the task
     */
    public void setMarked(boolean x){
        this.isMarked = x;
    }
}


