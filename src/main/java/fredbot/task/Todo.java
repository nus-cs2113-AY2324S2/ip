package fredbot.task;

import fredbot.exception.EmptyDescriptionException;

/**
 * Represents a todo that the user wants to add to their task list.
 * A Todo is a type of Task.
 */
public class Todo extends Task {

    /**
     * Constructs the Todo object.
     *
     * @param description Description of the todo.
     * @throws EmptyDescriptionException If the description of the todo is empty.
     */
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Converts the todo to a string for printing purposes.
     *
     * @return The done status and description of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo to a string for saving purposes.
     *
     * @return The done status and description of the todo.
     */
    public String saveString() {
        return "T" + super.saveString();
    }
}
