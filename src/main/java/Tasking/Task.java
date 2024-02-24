package Tasking;

import Tasking.Davvy;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        isDone = true;
        Davvy.printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(this);
        Davvy.printLine();
    }

    public void markNotDone() {
        isDone = false;
        Davvy.printLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(this);
        Davvy.printLine();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }
}
