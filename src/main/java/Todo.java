/**
 * Todo class represents a task with no specific deadline or event time.
 * It is a subtype of the Task class.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the todo task to a string representation for display.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task to a string representation for file storage.
     *
     * @return String representation of the todo task for file storage.
     */
    @Override
    public String toFileString() {
        return "T|" + super.toFileString();
    }
}
