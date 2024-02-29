package geepee.task;

public class Deadline extends Task {

    /** Deadline of task */
    private String by;

    /**
     * Initialises an instance of the Deadline class with completion status as false.
     * Calls the Task constructor to initialise the task description and completion status.
     * 
     * @param description Description of deadline task.
     * @param by Description of by when the task should be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Initialises an instance of the Deadline class with a given completion status.
     * Calls the Task constructor to initialise the task description and completion status.
     * 
     * @param description Description of deadline task.
     * @param by Description of by when the task should be completed.
     * @param isDone Completion status of task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    public String toString() {
        return String.format("[D]" + super.toString() + " (by: " + by + ")");
    }

    /**
     * Returns a string containing details of the deadline task for storage in the data file.
     */
    public String toFileFriendlyString() {
        return String.format("D;" + super.toFileFriendlyString() + ";" + by);
    }
}
