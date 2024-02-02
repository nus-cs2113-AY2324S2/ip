public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, String type) {
        super(description, type);
        this.by = by;
    }
    public void setBy(String by) {
        this.by = by;
    }
    public String getBy() {
        return by;
    }
}
