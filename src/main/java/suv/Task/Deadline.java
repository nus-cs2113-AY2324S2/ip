package suv.Task;

import suv.Task.Task;

/**
 * The Deadline class represents a task with a specific deadline.
 * It inherits from the Task class and adds functionality specific to Deadline tasks.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a new Deadline object with the specified description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * The representation includes the task type identifier, description, and deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}