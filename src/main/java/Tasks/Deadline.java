package Tasks;

import Tasks.Task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
