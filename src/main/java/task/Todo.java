package task;

public class Todo extends Task {
    private static final String TASK_TYPE = "[T]";

    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }
    
    @Override
    public String toSave() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return TASK_TYPE + super.toString();
    }
}
