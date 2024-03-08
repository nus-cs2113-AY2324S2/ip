public class Task {
    protected String description;
    protected boolean isDone;
    protected String type = null;
    /**
     * Constructs a task object with a placeholder description
     *
     */
    public Task() {
        this("[Add Description Here]");
    }
    /**
     * Constructs a task object with a given description
     *
     * @param description description set by user
     *
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns the status icon of a task as a string
     *
     * @return A string representing the status icon.
     *
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    /**
     * Returns a one bit binary number representing the mark status of a task
     *
     * @return An integer whose value is 1 if task is marked, 0 otherwise.
     *
     */
    public int getStatusFlag() {
        return (isDone ? 1 : 0);
    }
    /**
     * Sets task as done.
     *
     */
    public void markAsDone() {
        isDone = true;
    }
    /**
     * Sets task as not done.
     *
     */
    public void markAsNotDone() {
        isDone = false;
    }
    /**
     * Returns a string indicating the type of the task
     *
     * @return Type of task
     *
     */
    public String getType() {
        return type;
    }
    /**
     * Returns a string representing a task object in the list
     *
     * @return String representation of task in list
     *
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getType(), this.getStatusIcon(), this.description);
    }
    /**
     * Returns a string representing a task object in the cache file
     *
     * @param isFormatCache Formats the task into cache file format if true
     * @return String representation of task in cache file
     *
     */
    public String toString(boolean isFormatCache) {
            return String.format("%s | %d | %s", this.getType(), this.getStatusFlag(), this.description);
    }
}
