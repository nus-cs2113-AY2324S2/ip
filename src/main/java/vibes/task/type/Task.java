package vibes.task.type;

/**
 * Represents a task.
 */
public abstract class Task {
    public static final char MARKED = 'X';
    public static final char UNMARKED = ' ';
    public static final String PRINT_FORMAT = "[%s] %s";
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return 'X' if the task is done, ' ' otherwise
     */
    public char getStatusIcon() {
        if(isDone){
            return MARKED;
        }
        return UNMARKED;
    }

    /**
     * Sets the status of the task.
     *
     * @param done the status to be set
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description the description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the type of the task.
     *
     * @return the type of the task
     */
    public abstract String getTaskType();

    @Override
    public String toString(){
        return String.format(PRINT_FORMAT, this.getStatusIcon(), this.getDescription());
    }
}
