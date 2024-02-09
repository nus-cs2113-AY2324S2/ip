public class Deadline extends Task {

    protected String by;

    //Deadline subclass of Task superclass
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
