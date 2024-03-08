package lovie.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for ToDo.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the description of the todo.
     *
     * @return The description of the todo.
     */
    public String getDescription() {
        String[] splitUpDescription = description.trim().split("(?i)todo");
        return splitUpDescription[1].trim();
    }

    /**
     * Returns the icon of the todo.
     *
     * @return The icon of the todo.
     */
    @Override
    public String getTaskIcon() {
        return "T";
    }
}
