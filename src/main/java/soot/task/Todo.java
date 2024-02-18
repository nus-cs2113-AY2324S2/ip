package soot.task;

public class Todo extends Task {
    public Todo(String input, int counter) {
        super(input, counter);
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