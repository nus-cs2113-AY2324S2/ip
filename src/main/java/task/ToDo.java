package task;

/**
 * A type of task that only has data of its name, and
 * whether it has been completed or not.
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo task.
     * @param name Name to set the task's name as.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor for a ToDo task with an additional field for setting the
     * completetion status.
     * 
     * @param name Name to set the task's name as.
     * @param isCompleted Boolean for if the task is completed.
     */
    public ToDo(String name, boolean isCompleted) {
        super(name);
        this.isCompleted = isCompleted;
    }

    /**
     * Override method for printing a ToDo task as a string. Adds
     * a box denoting the type of task to the front of the string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
