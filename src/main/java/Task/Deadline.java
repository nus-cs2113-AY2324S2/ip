package Task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String get_date(){return by;}
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}