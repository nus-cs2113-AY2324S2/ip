package com.arriky.task;

abstract class Task {
    public String taskName;
    public boolean completed = false;

    Task(String taskName) {
        this.taskName = taskName;
        completed = false;
    }

    abstract String getSummary();
}
