package soot.task;

import soot.exceptions.SavedFileErrorTypeException;
import soot.parser.Parser;

public class Task {
    public String taskName;
    public boolean isDone;
    public static taskType taskType;

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

    //TODO: index of other elements are not updated
    public void printDelete() {
        System.out.println("okay, i will remove this task from your list: ");
        System.out.print("  >> ");
        printTaskType();
        printCheckbox();
    }

    public void printRespond() {
        System.out.println("Okay! i've added to ur tasklist:");
        System.out.print(" >> ");
        printTaskType();
        printCheckbox();
    }

    //TODO: after a task is marked done/undone, the taskCount printed when another task is added is unchanged
    public static void printTaskCount() {
        int taskCount = TaskList.listCounter;
        System.out.println("you now have " + taskCount + " tasks left...");
    }

    public void printTaskType() {
        System.out.print("[NA]");
    }

    public static taskType identifyTaskType(String input) throws SavedFileErrorTypeException {
        switch (input) {
        case "T":
            return soot.task.taskType.TODO;
        case "D":
            return soot.task.taskType.DEADLINE;
        case "E":
            return soot.task.taskType.EVENT;
        default:
            throw new SavedFileErrorTypeException();
        }
    }
}