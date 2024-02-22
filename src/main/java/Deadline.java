// add code here
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
    public String getCSV () {
        return "D" + "," + super.getCSV() + "," + by;
    }

    public String toString () {
        return "[D]" + super.toString() + " (do by: " + by + ")";
    }
}