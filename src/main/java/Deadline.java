/**
 * Tasks of the Deadline type, which extends Task by adding a "by" (deadline) variable.
 */
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * @return task type for representation in list form.
     */
    public String type() {
        return "[D]";
    }

    /**
     * @return a String for representation in list form.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
