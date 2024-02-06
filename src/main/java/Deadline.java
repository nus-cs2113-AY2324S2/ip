public class Deadline extends Task {
    private String dueDate;

    public Deadline(String line, int counter) {
        super(line, counter);
    }
//        index = counter + 1;
//        isDone = false;
//
//        int deadlineIndex = line.indexOf('/');
//        taskName = line.substring(0, deadlineIndex - 2);
//        dueDate = line.substring(deadlineIndex + 3);
//    }

    @Override
    public void printRespond() {
        System.out.println("Okay! i've added to ur tasklist: ");
        System.out.print(" >> ");
        printTasktype();
        super.printCheckbox();
        System.out.println(" " + this.taskName);
        super.printTaskcount();
    }
    @Override
    public void printTasktype() {
        System.out.print("[D]");
    }
}