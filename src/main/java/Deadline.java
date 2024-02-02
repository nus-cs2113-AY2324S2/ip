public class Deadline extends Task {
    protected String by;
    public Deadline (String taskName, String by) {
        super("D",taskName);
        this.by = by;
    }

    public String printTask() {
        return super.printTask() + "(by: " + this.by + ")";
    }
}
