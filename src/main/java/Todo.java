public class Todo extends Task {
    protected String description;

    public Todo(String description) {
        super(description);
        this.taskType = "T";

    }

    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}