/**
 * A task to be done and have no deadline
 */
public class Todo extends Task {
    //Attributes

    //Constructors
    public Todo (String description) {
        super(description);
        this.isDone = false;
        this.taskType = TaskType.TODO;
    }

    public Todo (String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    //Methods
    @Override
    /**
     * @return a CS representation of a Todo object.
     * For example, "T,0,study" means the user has to
     * study but he/she has not done it yet.
     */
    public String getCSV () {
        return "T" + "," + super.getCSV();
    }

    @Override
    /**
     * @return a String representation of a Todo object.
     */
    public String toString () {
        return "[T]" + super.toString();
    }
}// add code here