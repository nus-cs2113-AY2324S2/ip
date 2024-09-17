/**
 * Represents a specific type of task called "Todo" in the Jane task management application.
 * It extends the base Task class and provides a specialized string representation for Todo tasks.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Generates a string representation of the Todo task, including its specialized status icon.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}