package Tasks;

import Tasks.Task;

public class Todo extends Task {
    public String duration;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[T][X] " + super.description: "[T][ ] " + super.description); // mark done task with X
    }
}
