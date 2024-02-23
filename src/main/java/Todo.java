public class Todo extends Task {

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + description;
    }

    @Override
    public String saveTask(){
        return "T | " + (this.isDone() ? "1" : "0") + " | " + description;
    }
}