package tasktype;

/**
 * Represents the general Task of the JingHao chatbot.
 * It provides common functionality and properties for different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with a given description and the status of the task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void check(){
        this.isDone = true;
    }

    /**
     * Unmark the task as incomplete.
     */
    public void uncheck(){
        this.isDone = false;
    }

    /**
     * Restructure the task to the specific format to display on the screen.
     *
     * @return The formatted string containing the details of the task.
     */
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return "[" + statusIcon + "] " + description;
    }

    public abstract String toDiskFormat();

}
