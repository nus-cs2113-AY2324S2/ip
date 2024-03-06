public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskTypeLetter;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the icon to display whether the task is marked or not
     *
     * @return isDone Returns X if the tasks is marked, blank otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the icon to display whether the task is marked or not
     *
     * @return isDone Returns X if the tasks is marked, blank otherwise.
     */
    public int isDoneInt(){
        return (isDone ? 1 : 0);
    }

    /**
     * Rewrites the description of the task into a format suitable to save onto the text file
     *
     * @return The string of the task in the correct format
     */
    public String saveTaskDescription() {
        return (this.taskTypeLetter + "|" + this.isDoneInt() + "|" + this.description.trim());
    }

    /**
     * Returns marked status and description of the task
     *
     * @return The string of the task's marked status and description
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
    
    public void printTask(int numberOfListItems) {
        return;
    }
}
