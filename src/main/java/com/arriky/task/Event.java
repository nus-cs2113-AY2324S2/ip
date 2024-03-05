package com.arriky.task;

public class Event extends Task {
    private final String startTime;
    private final String endTime;

    Event(String taskName, String startTime, String endTime, boolean isCompleted) {
        super(taskName, 'E', isCompleted);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getSummary() {
        if (isCompleted) {
            return "[E][X] " + taskName + " (from: " + startTime + " to: " + endTime + ")";
        } else {
            return "[E][ ] " + taskName + " (from: " + startTime + " to: " + endTime + ")";
        }
    }

    @Override
    public String getSerializable() {
        return "D," + isCompleted + "," + taskName + "," + startTime + "," + endTime;
    }
}
