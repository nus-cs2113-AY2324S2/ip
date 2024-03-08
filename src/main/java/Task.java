public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructor for Task.
     *
     * @param description A string representing the task inputted.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the status of the task as done or not done.
     *
     * @param status The status indicates whether the task is done or not.
     */
    public void setDone(Boolean status) {
        this.isDone = status;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon indicating whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done or not done based on the given parameter.
     *
     * @param arrayOfCommand The array of command containing components of a task.
     */
    public void markAsDoneOrNotDone(String[] arrayOfCommand) {
        if (arrayOfCommand[0].equals("mark")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Returns the task in file format.
     *
     * @return The task in file format.
     */
    public String taskInFileFormat() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * Prints the task with its status icon and description.
     */
    public void printTask() {
        System.out.println(getStatusIcon() + " " + description);
    }
}
