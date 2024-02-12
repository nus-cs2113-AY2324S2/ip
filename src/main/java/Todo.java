public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    // Returns the status icon and description of the task
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}