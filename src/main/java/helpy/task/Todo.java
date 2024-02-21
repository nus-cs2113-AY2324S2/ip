package helpy.task;

import helpy.exceptions.IllegalDescriptionException;

public class Todo extends Task{
    public Todo(String command) throws IllegalDescriptionException {
        super();
        String description = command.replace("todo", "").trim();
        if (description.isEmpty()) {
            throw new IllegalDescriptionException();
        }
        taskName = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
