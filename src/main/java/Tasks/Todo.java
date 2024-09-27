package Tasks;

import Tasks.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, "T");
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
