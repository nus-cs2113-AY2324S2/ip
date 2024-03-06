package com.arriky.task;

import com.arriky.exception.ArrikyRuntimeException;

public class Event extends Task {
    private final ArrikyDateTime startTime;
    private final ArrikyDateTime endTime;

    Event(String taskName, String startTime, String endTime, boolean isCompleted) throws ArrikyRuntimeException {
        super(taskName, 'E', isCompleted);
        this.startTime = new ArrikyDateTime(startTime);
        this.endTime = new ArrikyDateTime(endTime);
    }

    @Override
    public String getSummary() {
        if (isCompleted) {
            return "[E][X] " + taskName + " (from: " + startTime.getDisplayDateTime() + " to: " + endTime.getDisplayDateTime() + ")";
        } else {
            return "[E][ ] " + taskName + " (from: " + startTime.getDisplayDateTime() + " to: " + endTime.getDisplayDateTime() + ")";
        }
    }

    @Override
    public String getSerializable() {
        return "D," + isCompleted + "," + taskName + "," + startTime.getSerializeDateTime() + "," + endTime.getSerializeDateTime();
    }
}
