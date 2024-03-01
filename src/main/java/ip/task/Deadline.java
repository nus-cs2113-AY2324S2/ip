package ip.task;

/**
 * A subclass of the Task class. Contains the additional information
 * of when the deadline is by
 */
public class Deadline extends Task {
    private static final int TASK_START_INDEX = 9;
    private String by;

    /**
     * Constructor to process the user's input and call the constructor of the
     * superclass with the appropriate information of the task's description passed.
     * Also extracts from the user's input when the deadline is by
     * and assigns to the by attribute
     *
     * @param line the user's input
     */
    public Deadline(String line) {
        super(line.substring(TASK_START_INDEX, line.indexOf("/by")));
        by = line.substring(line.indexOf("/by") + 4);
    }

    /**
     * Overloaded constructor that takes in the information needed for the object's
     * attributes directly instead of the user's input.
     * Calls the constructor of the superclass with the description passed
     * and assigns the isDone and by attributes
     *
     * @param isDone whether the deadline task is done
     * @param description the description of the deadline
     * @param by when the deadline is by
     */
    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.isDone = isDone;
        this.by = by;
    }

    /**
     * Returns a formatted String containing the icon that represents the
     * task type (Deadline), the status icon, and the description of the task
     *
     * @return a String specifying the object is a Deadline, if it is done, and its description
     */
    @Override
    public String getDetails() {
        return ("[D]" + super.getDetails() + "(by: " + by + ")");
    }

    public String getBy() {
        return by;
    }
}
