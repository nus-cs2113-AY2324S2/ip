package task;

public class Todo extends Task {
    private static final String TASK_TYPE = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TASK_TYPE + super.toString();
    }
}
