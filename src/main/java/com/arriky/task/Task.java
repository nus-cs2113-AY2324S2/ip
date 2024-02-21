package com.arriky.task;

public abstract class Task {
    public String taskName;
    public boolean completed;

    public char taskType;

    Task(String taskName, char taskType, boolean completed) {
        this.taskName = taskName;
        this.completed = completed;
        this.taskType = taskType;
    }

    public abstract String getSummary();

    public abstract String getSerializable();
}
