public class Todo extends Task {
    //Attributes

    //Constructors
    public Todo (String description) {
        super(description);
        this.isDone = false;
        this.taskType = TaskType.TODO;
    }

    //Methods
    @Override
    public String toString () {
        return "[T]" + super.toString();
    }
}// add code here