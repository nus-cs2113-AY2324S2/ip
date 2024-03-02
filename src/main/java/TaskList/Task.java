package TaskList;

public class Task {
    public String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;// Description of the task
        this.isDone = false;//Completion status of the task(True: Done, False: Undone)
    }

    /**
     * Retrieves the status icon indicating whether the task is done.
     *
     * @return A string representing the status icon. "[X]" if the task is done, "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");// mark tasks that are done with X, else empty space
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done by setting its completion status to false.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, combining its status icon and description.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return getStatusIcon() + " " + this.description; //combine the status and task description for easier listing
    }
    
}
