package soot.task;

import soot.manager.CommandManager;

public class Task {
    protected String taskName;
//    protected int index;
    protected boolean isDone;

    public Task(String input, Boolean isDone) {
        this.taskName = input;
        this.isDone = isDone;
    }
    public void printCheckbox() {
        if (isDone) {
            System.out.print("[/]");
        } else {
            System.out.print("[ ]");
        }
    }
    public void printTask(int index) {
        System.out.print(index + ".");
        printTaskType();
        printCheckbox();
        System.out.println(" " + taskName);
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("good job! this task is marked as done now: ");
        System.out.println(" >> " + this.taskName);
    }

    //TODO: removed printTaskType and printCheckbox due to error: 'void' type not allowed here
    public void markUndone() {
        if (!isDone) {
            System.out.println("hm, this task (" + this.taskName + ") had not been done yet. wrong one?");
            return;
        }
        this.isDone = false;
        System.out.println("This task is now marked undone: ");
        System.out.println("  >> " + this.taskName);
    }

    public void printRespond() {
        System.out.println("Okay! i've added to ur tasklist:");
        System.out.print(" >> ");
        printTaskType();
        printCheckbox();
    }

    //TODO: after a task is marked done/undone, the taskCount printed when another task is added is unchanged
    public void printTaskCount() {
        int taskCount = CommandManager.listCounter;
        System.out.println("you now have " + taskCount + " tasks left...");
    }

    public void printTaskType() {
        System.out.print("[NA]");
    }
}