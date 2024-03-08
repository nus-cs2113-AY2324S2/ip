package hachi.data.task;

/**
 * Represents a Deadline Task with the elements task name, its completeness status
 * and a class-level element that tracks the total number of tasks currently active.
 * Overrides superclass Task which adds a by date which represents the date of the deadline.
 *
 * @author clarencepohh
 * @version 01/03/2024
 */

public class Deadline extends Task {
    protected String byDate;

    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Increments the class-level element totalNumTasks by 1.
     * Deadlines are also considered as tasks.
     *
     * @param name The name of the task to be created.
     * @param by The date of the task's deadline as a string.
     */
    public Deadline(String name, String by) {
        super(name);
        this.byDate = by;
    }

    /**
     * Returns the task type of the specified task.
     * Override function of superclass Task.
     *
     * @return D which represents a Deadline task.
     */

    @Override
    public String getTaskType () {
        return "D";
    }

    /**
     * Returns the name of the task that invokes this function.
     * Override function of superclass Task.
     *
     * @return Returns the string of the task's name, including the by date.
     */
    @Override
    public String getName () {
        return name + " (by: " + byDate + ")";
    }

    /**
     * Returns the String save format used to save Hachi task data.
     * Override function of superclass Task.
     *
     * @return The required save format including the task type, status icon, name and by date.
     */

    @Override
    public String getSaveFormat () {
        return getTaskType() + " | " + getStatusIcon() + " | " + this.name + " | " + byDate;
    }
}
