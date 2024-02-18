package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "todo/" + this.isDone + "/" + this.description;
    }

    public String getDescription() {
        return "TODO: " + description;
    }
}
