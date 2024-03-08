package task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }
}