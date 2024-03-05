package com.arriky.task;

public class Deadline extends Task {

    private final String dueTime;
    Deadline(String taskName, String dueTime, boolean isCompleted) {
        super(taskName, 'D', isCompleted);
        this.dueTime = dueTime;
    }

    @Override
    public String getSummary() {
        if (isCompleted) {
            return "[D][X] " + taskName + " (by: " + dueTime + ")";
        } else {
            return "[D][ ] " + taskName + " (by: " + dueTime + ")";
        }
    }

    @Override
    public String getSerializable() {
        return "D," + isCompleted + "," + taskName + "," + dueTime;
    }
}
