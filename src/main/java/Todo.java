public class Todo extends Task {
    protected String description;
    protected String taskType;

    public Todo(String description, String taskType) {
        super(description);
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}