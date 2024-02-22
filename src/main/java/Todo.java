public class Todo extends Task{
    private String task;
    private boolean isComplete;
    public Todo(String task)
    {
        super(task);
    }
    public String toString() {
        return "[T]" + super.toString();
    }
}
