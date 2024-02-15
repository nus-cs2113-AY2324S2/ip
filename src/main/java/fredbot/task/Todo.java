package fredbot.task;

import fredbot.exception.EmptyTodoException;

public class Todo extends Task {
    public Todo(String description) throws EmptyTodoException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyTodoException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
