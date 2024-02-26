package natsu.task;

/**
 * Represents a todo task in a task management application.
 * A todo is a basic task type that is characterized only by a description.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} instance with the specified description.
     * Inherits the basic task structure and initializes with the given description.
     *
     * @param description The descriptive text of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task, including its type, status icon, and description.
     * Prepends "[T]" to indicate that this task is of type todo, followed by the status icon
     * and description from the {@code Task} superclass.
     *
     * @return A string that represents the todo task, indicating its type, completion status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
