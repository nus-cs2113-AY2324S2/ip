package chatman.tasks;

/**
 * Implements template for any task with a deadline.
 *
 * @author LWachtel1
 * */
public class Deadline extends Task {
    private String deadlineSymbol = "[D]";
    private String by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of task (that has deadline).
     * @param by Deadline date in String form.
     */
    public Deadline(String description, String by, String command) {
        super(description, command);
        this.by = by;
    }

    /**
     * Returns Deadline subclass label, completion checkbox, task description, and deadline date as 1 String.
     *
     * @return String representation of Deadline: [D], [X] or [ ], task description, deadline date as string.
     */
    @Override
    public String toString() {
        return deadlineSymbol + super.toString() + " (by: " + by + ")";
    }

}
