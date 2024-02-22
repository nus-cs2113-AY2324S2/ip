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
    public String getCSV () {
        return "T" + "," + super.getCSV();
    }

    public String toString () {
        return "[T]" + super.toString();
    }
}// add code here