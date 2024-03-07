public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
        this.isDone = isDone;
    }

    public String getType() {
        return "[T]";
    }
    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}