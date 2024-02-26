package Tasks;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String content, boolean isDone, String by) {
        super(content, isDone);
        this.dueDate = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
