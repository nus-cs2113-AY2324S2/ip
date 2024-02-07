public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String bye) {
        this.by = bye;
    }

    @Override
    public String toString() {
        String res = "[D]" + super.toString();
        res += " (by: " + by + ")";
        return res;
    }
}
