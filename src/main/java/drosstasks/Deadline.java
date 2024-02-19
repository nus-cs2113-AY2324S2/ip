package drosstasks;

import drosstasks.Task;

public class Deadline extends Task {

    protected String by;

    //drosstasks.Deadline subclass of drosstasks.Task superclass
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String statusMark = this.isCompleted() ? "x" : " "; // Mark with 'x' if completed
        return "[D]" + "[" + statusMark + "] " + super.toString() + " (by: " + by + ")";
    }
}
