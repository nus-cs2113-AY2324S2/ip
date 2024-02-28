package Gene.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return super.toFileString();
    }
}
