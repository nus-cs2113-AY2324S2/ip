package tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }


    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + description;

    }
}