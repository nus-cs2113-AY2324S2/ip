public class Deadline extends Task {
    private String dueDate;

    public Deadline(String taskName, int counter, String dueDate) {
        super(taskName, counter);
        this.dueDate = dueDate;
    }

    @Override
    public void printRespond() {
        super.printRespond();
        System.out.println(" " + this.taskName + " (by: " + dueDate + "!)");
        super.printTaskcount();
    }
    @Override
    public void printTasktype() {
        System.out.print("[D]");
    }
    @Override
    public void printTask() {
        super.printTask();
        System.out.println("    >> by: " + dueDate + "!!");
    }
}