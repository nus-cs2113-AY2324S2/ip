package kurobot;

public class Deadline extends Task {
    protected String by;

    public Deadline (String taskName, String by, boolean isMarked) {
        super("D",taskName, isMarked);
        this.by = by;
    }

    public String printTask() {
        return super.printTask() + "(by: " + this.by + ")";
    }
}
