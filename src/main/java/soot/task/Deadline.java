package soot.task;

public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public void printDelete() {
        super.printDelete();
        System.out.println(" " + this.taskName + " (by: " + dueDate + "!)");
        super.printTaskCount();
    }

    @Override
    public void printRespond() {
        super.printRespond();
        System.out.println(" " + this.taskName + " (by: " + dueDate + "!)");
        super.printTaskCount();
    }

    @Override
    public void printTaskType() {
        System.out.print("[D]");
    }
    @Override
    public void printTask(int index) {
        super.printTask(index);
        System.out.println("    >> by: " + dueDate + "!!");
    }
}