public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }

    public String toString() {
        return "[T]" + super.toString();
    }

}