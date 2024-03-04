/**
 * Represents a task.
 * String <code>description</code> represents the textual description given to the task.
 * boolean <code>isDone</code> represents the complete status of the task.
 * Either done (true) or not done (false).
 * char <code>type</code> represents the task type toDo, event, deadline or none of them.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = ' ';
    }
    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Construct a new task with the specified description, type and isDone status.
     *
     * @param description Description of the task.
     * @param type Task type toDo, event, deadline or none of them.
     * @param isDone Indicate if the task is marked as done.
     * @throws IllegalArgumentException If zone is <= 0.
     */
    public Task(String description, char type, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Returns the status icon assigned to the task.
     * Icon should be "X" if the task is done and " " if not done.
     */
    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    /**
     * Returns the type icon assigned to the task.
     * Icon equals to the attribute type.
     */
    public char getTypeIcon() {
        return type;
    }

    /**
     * Update the task description to a new one.
     *
     * @param newDescription New description given to the task.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
}
