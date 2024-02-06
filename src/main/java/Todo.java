public class Todo extends Task {
    public Todo(String input, int counter) {
        super(input, counter);
    }

    @Override
    public void printRespond() {
        super.printRespond();
        System.out.println(" " + this.taskName);
        super.printTaskcount();
    }
    @Override
    public void printTasktype() {
        System.out.print("[T]");
    }
}