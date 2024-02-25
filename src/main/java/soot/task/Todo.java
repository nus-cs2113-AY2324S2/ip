package soot.task;

public class Todo extends Task {
    public Todo(String input, Boolean isDone) {
        super(input, isDone);
        this.taskType = soot.task.taskType.TODO;
    }
    @Override
    public void printDelete() {
        super.printDelete();
        System.out.println(" " + this.taskName);
        super.printTaskCount();
    }

    @Override
    public void printRespond() {
        super.printRespond();
        System.out.println(" " + this.taskName);
        super.printTaskCount();
    }
    @Override
    public void printTaskType() {
        System.out.print("[T]");
    }
}