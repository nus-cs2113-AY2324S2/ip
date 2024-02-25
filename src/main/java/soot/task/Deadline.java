package soot.task;

public class Deadline extends Task {
    public String dueDate;

    public Deadline(String taskName, Boolean isDone, String dueDate) {
        super(taskName, isDone);
        this.taskType = soot.task.taskType.DEADLINE;
        this.dueDate = dueDate;
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