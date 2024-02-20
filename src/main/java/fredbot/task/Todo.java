package fredbot.task;

import fredbot.exception.EmptyDescriptionException;

public class Todo extends Task {
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveString() {
        return "T" + super.saveString();
    }
}
