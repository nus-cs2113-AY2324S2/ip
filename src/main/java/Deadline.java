// add code here

/**
 * A deadline class containing
 * deadline's name and date
 */
public class Deadline extends Task {
    //Attributes

    protected String by;

    //Constructors

    public Deadline (String name, String by) {
        super(name);
        this.by = by;
        this.taskType = TaskType.DEADLINE;
    }

    public Deadline (String name, String by, boolean isDone) {
        this(name, by);
        this.isDone = isDone;
    }
    //Methods

    /**
     * @return a CS representation of a Deadline object.
     * For example, "D,1,CS2113 Weekly Quiz,8/3/2024 2359"
     * means the user already finished (1) a deadline
     * named "CS2113 Weekly Quiz" due 8/3/2024 at 2359.
     */
    public String getCSV () {
        return "D" + "," + super.getCSV() + "," + by;
    }

    /**
     * @return a String representation of a Deadline object
     */
    public String toString () {
        return "[D]" + super.toString() + " (do by: " + by + ")";
    }
}