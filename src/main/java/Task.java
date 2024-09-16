public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the status of a task to "Done"
     * Prints out the type icon of the task as well as its "Done" status and description.
     *
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println(getTypeIcon() + getStatusIcon() + this.description);
    }

    public void markAsDoneWithoutPrints() {
        this.isDone = true;
    }

    /**
     * Sets the status of a task to "Not Done"
     * Prints out the type icon of the task as well as its "Done" status and description.
     */
    public void markAsUndone() {
        this.isDone = false;
        System.out.println(getTypeIcon() + getStatusIcon() + this.description);
    }

    public void markAsUndoneWithoutPrints() {
        this.isDone = false;
    }

    /**
     * Returns an icon based on the "Done" status of the task, [X} if done and [ ] if not done
     * @return status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Prints all relevant info relating to a task, such as its type, "Done" status and description
     */
    public void printTask() {
        return;
    }

    /**
     * Returns the type icon of the task, "[T]" if ToDo, "[E]" if Event, "[D]" if Deadline
     * @return type icon of the task
     */
    public String getTypeIcon(){
        return "?";
    }
}