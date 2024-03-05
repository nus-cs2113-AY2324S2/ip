package com.arriky.task;

public abstract class Task {
    public String taskName;
    public boolean isCompleted;

    public char taskType;

    Task(String taskName, char taskType, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        this.taskType = taskType;
    }

    public abstract String getSummary();

    public abstract String getSerializable();
}
