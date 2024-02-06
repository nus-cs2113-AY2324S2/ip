public class Todo extends Task {
    public Todo(String line, int counter) {
        super(line, counter);
    }

    @Override
    public void printRespond() {
        System.out.println("Okay! i've added to ur tasklist: ");
        System.out.print(" >> ");
        super.printCheckbox();
        System.out.println(" " + this.taskName);
        super.printTaskcount();
    }
}