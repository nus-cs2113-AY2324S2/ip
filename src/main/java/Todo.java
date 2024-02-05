public class Todo extends Task {
    protected String toDoDescription;

    public Todo(String description, String toDoDescription) {
        super(description);
        this.toDoDescription = toDoDescription;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[T][X] " + super.description: "[T][ ] " + super.description); // mark done task with X
    }
}
