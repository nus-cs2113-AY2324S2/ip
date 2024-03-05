package com.arriky.task;

public class Deadline extends Task{

    private String dueTime;
    Deadline(String taskName, String dueTime, boolean completed) {
        super(taskName, 'D', completed);
        this.dueTime = dueTime;
    }

    @Override
    public String getSummary() {
        if (completed) {
            return "[D][X] " + taskName + " (by: " + dueTime + ")";
        } else {
            return "[D][ ] " + taskName + " (by: " + dueTime + ")";
        }
    }

    @Override
    public String getSerializable() {
        return "D," + completed + "," + taskName + "," + dueTime;
    }
}
