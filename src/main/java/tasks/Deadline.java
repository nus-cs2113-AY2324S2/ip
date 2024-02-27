package tasks;

/**
 * Represents a Deadline-type task to be tracked by Dor. Subclass of Task
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor for Deadline when no task name or task done status is provided.
     * Invokes the superclass constructor with no parameters
     *
     * @param by Date/time the task has to be done by
     */
    public Deadline(String by) {
        super();
        this.by = by;
    }

    /**
     * Constructor for Deadline when task name is provided but task done status is not.
     * Invokes the superclass constructor with one parameter
     *
     * @param name Name of the task
     * @param by Date/time the task has to be done by
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    /**
     * Constructor for Deadline when task name and task done status are provided.
     * Invokes the superclass constructor with two parameters
     *
     * @param name Name of the task
     * @param isDone Whether the task is done or not
     * @param by Date/time the task has to be done by
     */
    public Deadline(String name, boolean isDone, String by) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Returns the task-type "Deadline", represented by the String "D"
     *
     * @return String representation of the task being of "Deadline" type
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the date/time the task has to be done by
     *
     * @return Date/time the task has to be done by
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a String representation of the task for printing
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + this.by + ")");
    }
}
