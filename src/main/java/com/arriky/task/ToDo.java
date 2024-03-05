package com.arriky.task;
public class ToDo extends Task {
    ToDo(String taskName, boolean isCompleted) {
        super(taskName, 'T', isCompleted);
    }

    @Override
    public String getSummary() {
        if (isCompleted) {
            return "[T][X] " + taskName;
        } else {
            return "[T][ ] " + taskName;
        }
    }

    @Override
    public String getSerializable() {
        return "T," + isCompleted + "," + taskName;
    }
}
