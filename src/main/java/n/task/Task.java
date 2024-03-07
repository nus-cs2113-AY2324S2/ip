/**
 * The base class representing a Task.
 *
 * @author  anneleong
 * @version 1.0
 * @since   2024-03-07
 */
package n.task;

public class Task {
    // Fields to store task information
    protected String description;
    protected int index;
    protected boolean isDone;
    protected Type taskType;
    /**
     * Constructor to initialize a Task object.
     *
     * @param description The description of the task.
     * @param arrayIndex  The index of the task in the array.
     */
    public Task(String description, int arrayIndex) {
        this.description = description.trim();
        this.index = arrayIndex + 1;
        this.isDone = false;
    }
    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the task.
     *
     * @param description The new task description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Gets the index of the task.
     *
     * @return The task index.
     */
    public int getIndex() {
        return index;
    }
    /**
     * Sets the index of the task.
     *
     * @param index The new task index.
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * Sets the task as done or not done.
     *
     * @param done True if the task is done, false otherwise.
     */
    public void setDone(boolean done) {
        isDone = done;
    }
    /**
     * Gets the checkmark symbol ('X' if done, ' ' if not done).
     *
     * @return The checkmark symbol.
     */
    public String getCheckMark() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }
    /**
     * Gets the type of the task.
     *
     * @return The task type (Event, Deadline, ToDo).
     */
    public Type getTaskType() {
        return taskType;
    }
    /**
     * Overrides the default toString method to represent the task as a string.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return this.getIndex()+ ". ["+ this.getCheckMark()+"] " +this.getDescription();
    }
}