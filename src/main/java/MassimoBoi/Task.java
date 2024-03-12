package MassimoBoi;

/**
 * Represents a task that the user wishes to complete.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a new task and marks it as not done.
     *
     * @param description the description of the task.
     */
    public Task(String description){
        this.description = description;
        isDone = false;
    }

    /**
     * Returns status of a task.
     *
     * @return a String [X] when task is done and [] when it is not.
     */
    public String getStatus(){
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns description of task.
     * @return a String containing the task description.
     */
    public String getDescription(){
        return (this.description);
    }

    /**
     * Marks task as done.
     */
    public void markAsDone(){
        isDone = true;
    }

    /**
     * Unmarks a task.
     */
    public void unmark(){
        isDone = false;
    }

    /**
     * Returns the task type of the task.
     *
     * @return the task type, or [U] if task is not todo, deadline, or event.
     */
    public String taskType(){
        return "[U]";
    }




}
