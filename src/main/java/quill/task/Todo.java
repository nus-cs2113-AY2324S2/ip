package quill.task;

/**
 * The Todo class represents a task of type "Todo" in the Quill application.
 * It extends the Task class and includes specific behavior for todo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specific description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task for printing.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task for storing.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String saveTask() {
        return "T | " + super.saveTask();
    }
}
