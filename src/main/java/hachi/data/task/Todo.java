package hachi.data.task;

/**
 * Represents a Todo Task with the elements task name, its completeness status
 * and a class-level element that tracks the total number of tasks currently active.
 * Overrides superclass Task.
 *
 * @author clarencepohh
 * @version 01/03/2024
 */

public class Todo extends Task {

    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Increments the class-level element totalNumTasks by 1.
     * Todos are also considered as tasks.
     *
     * @param name The name of the task to be created.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the task type of the specified task.
     * Override function of superclass Task.
     *
     * @return T which represents a Todo task.
     */

    @Override
    public String getTaskType () {
        return "T";
    }

    /**
     * Returns the String save format used to save Hachi task data.
     * Override function of superclass Task.
     *
     * @return The required save format including the task type, status icon and name.
     */

    @Override
    public String getSaveFormat () {
        return getTaskType() + " | " + getStatusIcon() + " | " + getName();
    }
}
