package geepee.task;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    public String toString() {
        return String.format("[D]" + super.toString() + " (by: " + by + ")");
    }

    public String toFileFriendlyString() {
        return String.format("D;" + super.toFileFriendlyString() + ";" + by);
    }
}
