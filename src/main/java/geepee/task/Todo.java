package geepee.task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public String toString() {
        return String.format("[T]" + super.toString());
    }
}
