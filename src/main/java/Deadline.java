// add code here

/**
 * Represents a deadline
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
     * Get a String of the deadline's CSV format.
     * The format is "D + status + name + date".
     * For example: "D,1,CS2113 weekly quiz,8/3/24" means
     * that the user finished (1) the deadline (CS2113 weekly quiz)
     * by the date (8/3/24).
     *
     * Useful when saving a Deadline object to a file.
     * @return the CSV representation of the object
     */
    public String getCSV () {
        return "D" + "," + super.getCSV() + "," + by;
    }

    /**
     * Get the String representation of the object
     * @return a String containing the deadline's status, name and date.
     */
    public String toString () {
        return "[D]" + super.toString() + " (do by: " + by + ")";
    }
}