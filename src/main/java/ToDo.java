public class ToDo extends Task {
    public ToDo (String description){
        super(description);
    }

    // Prints task
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}