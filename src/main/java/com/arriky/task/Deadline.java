package com.arriky.task;

import com.arriky.exception.ArrikyRuntimeException;

/**
 * Class for an deadline type task in the tasklist.
 * @author Songyue Wang
 */
public class Deadline extends Task {
    private final ArrikyDateTime dueTime;

    /**
     * Constructor of deadline.
     * @param taskName Name of the deadline.
     * @param dueTime Due time of the deadline, e.g. <code>2024-03-06</code> or <code>2024-03-06 18:00</code>.
     * @param isCompleted Whether this deadline is completed (true/false).
     */
    public Deadline(String taskName, String dueTime, boolean isCompleted) throws ArrikyRuntimeException {
        super(taskName, isCompleted);
        this.dueTime = new ArrikyDateTime(dueTime);
    }

    /**
     * Get summary of the deadline.
     * @return String representation of the deadline to be printed in UI.
     */
    @Override
    public String getSummary() {
        return "[D][" + this.getCompletedSign() + "] " + this.getTaskName() + " (by: " + dueTime.getDisplayDateTime() + ")";
    }

    /**
     * Get the serializable string describing the deadline.
     * @return String representation of the deadline to be saved to local file.
     */
    @Override
    public String getSerializable() {
        return "D," + this.getCompletedStatus() + "," + this.getTaskName() + "," + dueTime.getSerializeDateTime();
    }
}
