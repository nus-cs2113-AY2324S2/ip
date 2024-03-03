package ava.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadlineEntered) {
        super(description);
        deadlineEntered = deadlineEntered.replace("by", "by:");
        this.deadline = deadlineEntered;
    }

    public Deadline(String description, String deadlineEntered, boolean isCompleted) {
        super(description, isCompleted);
        deadlineEntered = deadlineEntered.replace("by", "by:");
        this.deadline = deadlineEntered;
    }

    /**
     * Converts a Deadline object to a string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }
}
