package Tasks;

/**
 * The Todo class represents a to-do task.
 * It extends the Task class and provides specific behavior for to-do tasks.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo object with the given content and completion status.
     *
     * @param content The content of the to-do task.
     * @param isDone  The completion status of the to-do task (true if completed, false otherwise).
     */

    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }

    /**
     * Returns a string representation of the to-do task.
     * The string representation includes the task type identifier "[T]" and its content.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
