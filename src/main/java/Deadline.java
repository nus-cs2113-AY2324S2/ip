public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline
     *
     * @param description task description
     * @param by deadline by which to complete task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns task description, whether it has been completed , label for Deadline subclass
     * and its deadline
     *
     * @return [D] label, completion status of task, task description, task deadline as strin
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

}
