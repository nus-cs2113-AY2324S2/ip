package mona.task;
/**
 * Represents a Todo task with a description. The Todo class
 * is a subclass of the Task class.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo. Initializes the Todo task with a description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns a string representation of the Todo task, including its type and description.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
