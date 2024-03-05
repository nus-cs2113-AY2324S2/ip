package com.arriky.task;
public class ToDo extends Task {
    ToDo(String taskName, boolean completed) {
        super(taskName, 'T', completed);
    }

    @Override
    public String getSummary() {
        if (completed) {
            return "[T][X] " + taskName;
        } else {
            return "[T][ ] " + taskName;
        }
    }

    @Override
    public String getSerializable() {
        return "T," + completed + "," + taskName;
    }
}
