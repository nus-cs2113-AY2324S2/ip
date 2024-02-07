public class Todo extends Task {
    //Attributes

    //Constructors
    public Todo(String description){
        super(description);
        isDone = false;
    }

    //Methods
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}// add code here