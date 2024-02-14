package Tasks;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(int id, String content, boolean isDone, String by) {
        super(id, content, isDone);
        this.dueDate = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
