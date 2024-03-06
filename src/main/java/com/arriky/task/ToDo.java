package com.arriky.task;

/**
 * Class for a to-do type task in the tasklist.
 * @author Songyue Wang
 */
public class ToDo extends Task {
    /**
     * Constructor of to-do.
     * @param taskName Name of the to-do.
     * @param isCompleted Whether this to-do is completed (true/false).
     */
    public ToDo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    /**
     * Get summary of the to-do.
     * @return String representation of the to-do to be printed in UI.
     */
    @Override
    public String getSummary() {
        return "[T][" + this.getCompletedSign() + "] " + this.getTaskName();
    }

    /**
     * Get the serializable string describing the to-do.
     * @return String representation of the to-do to be saved to local file.
     */
    @Override
    public String getSerializable() {
        return "T," + this.getCompletedStatus() + "," + this.getTaskName();
    }
}
