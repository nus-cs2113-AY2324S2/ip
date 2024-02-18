public class Todo extends Task{

    public Todo(String description, boolean isDone) {
        super(description);
    }

    public String getDescription(){
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription();
    }






}
