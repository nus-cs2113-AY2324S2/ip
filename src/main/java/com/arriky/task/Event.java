package com.arriky.task;

public class Event extends Task {
    private String startTime;
    private String endTime;

    Event(String taskName, String startTime, String endTime, boolean completed) {
        super(taskName, 'E', completed);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getSummary() {
        if (completed) {
            return "[E][X] " + taskName + " (from: " + startTime + " to: " + endTime + ")";
        } else {
            return "[E][ ] " + taskName + " (from: " + startTime + " to: " + endTime + ")";
        }
    }

    @Override
    public String getSerializable() {
        return "D," + completed + "," + taskName + "," + startTime + "," + endTime;
    }
}
