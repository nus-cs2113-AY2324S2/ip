package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toDisk() {
        return "T" + super.toDisk() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
