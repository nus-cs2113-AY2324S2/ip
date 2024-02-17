package geepee.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    public String toString() {
        return String.format("[T]" + super.toString());
    }

    public String toFileFriendlyString() {
        return String.format("T;" + super.toFileFriendlyString());
    }
}
