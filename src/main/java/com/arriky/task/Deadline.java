package com.arriky.task;

import com.arriky.exception.ArrikyRuntimeException;

public class Deadline extends Task {

    private final ArrikyDateTime dueTime;
    Deadline(String taskName, String dueTime, boolean isCompleted) throws ArrikyRuntimeException {
        super(taskName, 'D', isCompleted);
        this.dueTime = new ArrikyDateTime(dueTime);
    }

    @Override
    public String getSummary() {
        if (isCompleted) {
            return "[D][X] " + taskName + " (by: " + dueTime.getDisplayDateTime() + ")";
        } else {
            return "[D][ ] " + taskName + " (by: " + dueTime.getDisplayDateTime() + ")";
        }
    }

    @Override
    public String getSerializable() {
        return "D," + isCompleted + "," + taskName + "," + dueTime.getSerializeDateTime();
    }
}
