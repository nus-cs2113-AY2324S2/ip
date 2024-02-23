package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String isDoneIcon = super.isDone ? "X" : " ";
        return "[T][" + isDoneIcon + "] " + super.description;
    }
}
