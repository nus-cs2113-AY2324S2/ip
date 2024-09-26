package com.arriky.task;

/**
 * Abstract class for a task, to be inherited by to-do, event and deadline.
 * @author Songyue Wang
 */
public abstract class Task {
    private String taskName;
    private boolean isCompleted;

    /**
     * Constructor of task.
     * @param taskName Name of the task
     * @param isCompleted Whether this task is completed (true/false).
     */
    Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * Set a task as completed.
     */
    protected void setAsCompleted() {
        isCompleted = true;
    }

    /**
     * Undo setting of a task as completed.
     */
    protected void setAsIncomplete() {
        isCompleted = false;
    }

    /**
     * Get task name.
     * @return Name of the task.
     */
    protected String getTaskName() {
        return taskName;
    }

    /**
     * Get whether the task is already marked completed (true/false).
     * @return Boolean representing the completion status.
     */
    protected Boolean getCompletedStatus() {
        return isCompleted;
    }

    /**
     * Get whether the task is already marked completed.
     * @return A single-character string (<code>X</code> for completed, <code> </code> for incomplete).
     */
    protected String getCompletedSign() {
        if (isCompleted) {
            return "X";
        }
        return " ";
    }

    /**
     * Abstract method for getting string to display, to be implemented in sub-classes.
     * @return String to be printed on the screen.
     */
    protected abstract String getSummary();

    /**
     * Abstract method for getting string to save, to be implemented in sub-classes.
     * @return String to be saved to local file.
     */
    protected abstract String getSerializable();
}
