package tasks;

/**
 * Deadline subclass of Task. Requiring a by input.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for the deadline object.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for the deadline object when reconstructing the list
     * from nocturne.txt, which includes the completion status of the deadline.
     *
     * @param description The description of the deadline.
     * @param by The due date of the deadline.
     * @param isDone The boolean variable of whether the deadline is done or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Override of the deadline class when it is printed,
     * resulting in the format shown in the list.
     *
     * @return The string that will be printed.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + this.description + " (by: " + by + ")";
    }
}

