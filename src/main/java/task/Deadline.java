package task;



public class Deadline extends Task {
    protected String by;
    public Deadline(String description, String by) {
        super(description);
        this.type = "D";
        this.by = by;
    }

    @Override
    public String toString() {
        String isDoneIcon = super.isDone ? "X" : " ";
        return "[" + type + "][" + isDoneIcon + "]" + super.description + " (by: " + by + ")";
    }
}
