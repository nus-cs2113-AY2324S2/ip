package kurobot;

/**
 * A type of task with a deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Store the given task name, deadline and marking status.
     *
     * @param taskName Name of the task.
     * @param by Deadline of the task.
     * @param isMarked Whether the task is marked or not.
     */
    public Deadline (String taskName, String by, boolean isMarked) {
        super("D", taskName, isMarked);
        this.by = by;
    }

    public String printTask() {
        return super.printTask() + "(by: " + this.by + ")";
    }
}
