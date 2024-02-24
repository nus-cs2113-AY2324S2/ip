public class Todo extends Task{
    private final static char TASK_TYPE = 'T';

    public Todo (String description){
        super(description);
    }

    public char getTaskType(){
        return TASK_TYPE;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
