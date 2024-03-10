package tasks;


/**
 * Represents a normal task.
 */
public class Todo extends Task {
    // METHODS
    /**
     * Constructs Todo.
     */
    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
