package com.arriky.task;

import com.arriky.exception.ArrikyRuntimeException;

/**
 * Class for an event type task in the tasklist.
 * @author Songyue Wang
 */
public class Event extends Task {
    private final ArrikyDateTime startTime;
    private final ArrikyDateTime endTime;

    /**
     * Constructor of event.
     * @param taskName Name of the event.
     * @param startTime Start time of the event, e.g. <code>2024-03-06</code> or <code>2024-03-06 18:00</code>.
     * @param endTime End time of the event, e.g. <code>2024-03-06</code> or <code>2024-03-06 18:00</code>.
     * @param isCompleted Whether this event is completed (true/false).
     */
    public Event(String taskName, String startTime, String endTime, boolean isCompleted) throws ArrikyRuntimeException {
        super(taskName, isCompleted);
        this.startTime = new ArrikyDateTime(startTime);
        this.endTime = new ArrikyDateTime(endTime);
    }

    /**
     * Get summary of the event.
     * @return String representation of the event to be printed in UI.
     */
    @Override
    public String getSummary() {
        return "[E][" + this.getCompletedSign() + "] " + this.getTaskName() + " (from: " + startTime.getDisplayDateTime() + " to: " + endTime.getDisplayDateTime() + ")";
    }

    /**
     * Get the serializable string describing the event.
     * @return String representation of the event to be saved to local file.
     */
    @Override
    public String getSerializable() {
        return "D," + this.getCompletedStatus() + "," + this.getTaskName() + "," + startTime.getSerializeDateTime() + "," + endTime.getSerializeDateTime();
    }
}
