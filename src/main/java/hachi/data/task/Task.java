package hachi.data.task;

/**
 * Represents a Task with the elements task name, its completeness status
 * and a class-level element that tracks the total number of tasks currently active.
 *
 * @author clarencepohh
 * @version 01/03/2024
 */

public class Task {
    protected String name;
    protected boolean isCompleted;
    private static int totalNumTasks = 0;

    /**
     * Constructor for new tasks given its name.
     * Tasks are initialized as incomplete.
     * Increments the class-level element totalNumTasks by 1.
     *
     * @param name The name of the task to be created.
     */

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
        totalNumTasks += 1;
    }

    /**
     * Returns the name of the task that invokes this function.
     *
     * @return Returns the string of the task's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the task's name to the given name.
     *
     * @param name The name to set the task's name to.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if a task is complete and returns true if complete, false if incomplete.
     *
     * @return The boolean value that represents whether a task is completed:
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Sets the completeness status of a task.
     *
     * @param completed Boolean value to set the task to.
     */

    public void setCompleteness(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Retrieves the class-level counter of total number of tasks active.
     *
     * @return The total number of tasks active.
     */

    public static int getTotalNumTasks() {
        return totalNumTasks;
    }

    /**
     * Decrements the class-level counter of total number of tasks active.
     *
     */

    public static void decrementTotalNumTasks() {
        totalNumTasks -= 1;
    }

    /**
     * Checks the completeness status of a task and returns to the
     * caller the status icon of the task.
     * "X" for complete, " " for incomplete.
     *
     * @return String representation of completeness status icon.
     */

    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Returns the task type of the specified task.
     *
     * @return ? which represents an unclassified task.
     */

    public String getTaskType() {
        return "?";
    }

    /**
     * Returns the String save format used to save Hachi task data.
     *
     * @return ? which represents an unclassified task.
     */

    public String getSaveFormat () {
        return "?";
    }
}
