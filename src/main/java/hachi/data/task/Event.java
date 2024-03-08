package hachi.data.task;

/**
 * Represents an Event Task with the elements task name, its completeness status
 * and a class-level element that tracks the total number of tasks currently active.
 * Overrides superclass Task which adds a start and end date which represents
 * the starting date of the event and the ending date of the event respectively.
 *
 * @author clarencepohh
 * @version 01/03/2024
 */

public class Event extends Task {
    protected String startDate;
    protected String endDate;

    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Increments the class-level element totalNumTasks by 1.
     * Events are also considered as tasks.
     *
     * @param name The name of the task to be created.
     * @param start The starting time/date of the task.
     * @param end The ending time/date of the task.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Returns the task type of the specified task.
     * Override function of superclass Task.
     *
     * @return E which represents an Event task.
     */

    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the name of the task that invokes this function.
     * Override function of superclass Task.
     *
     * @return Returns the string of the task's name, including the start and end date.
     */

    @Override
    public String getName () {
        return name + "(from: " + startDate + " to: " + endDate + ")";
    }

    /**
     * Returns the String save format used to save Hachi task data.
     * Override function of superclass Task.
     *
     * @return The required save format including the task type, status icon, name, start and end date.
     */

    @Override
    public String getSaveFormat () {
        return getTaskType() + " | " + getStatusIcon() + " | " + this.name + " | " + startDate + " | " + endDate;
    }
}
