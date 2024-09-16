package task;

/**
 * This Todo class represents a task with no associated time or date.
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a Todo object.
     *
     * @return the string representation of a Todo object.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
