package duke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toDisk() {
        return "D" + super.toDisk() + " | " + this.by + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
