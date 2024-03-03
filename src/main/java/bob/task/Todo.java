package bob.task;

/**
 * Todo Task class. Used to represent Tasks with no timeframe restrictions.
 */
public class Todo extends Task {
    private Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    /**
     * Constructor to create a Todo Task Object.
     *
     * @param taskName Name of Todo Task.
     */
    public Todo(String taskName) {
        super(taskName, false);
    }

    public Task markTaskAsComplete() {
        return new Todo(taskName, true);
    }

    public Task markTaskAsIncomplete() {
        return new Todo(taskName, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
