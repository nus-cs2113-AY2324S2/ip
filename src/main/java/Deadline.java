public class Deadline extends Task {
    public static final String TYPE = "D";
    private String by;

    public Deadline(String by) {
        super();
        this.by = by;
    }

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + this.by + ")");
    }
}
