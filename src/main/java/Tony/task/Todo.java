package Tony.task;

public class Todo extends Task {
    /**
     * Represents a {@code Todo} object that saves task type <code>todo</code>
     * @param description is the String <code>description</code> for todo tasks
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of tasks to identify todo.
     * @return String <code>[T]</code> as task type
     */

    public String type() {
        return "[T]";
    }

    /**
     * Returns the String to save the current task list
     * @return String formatted to be saved in task list.
     */

    @Override
    public String toString() {
        return type() + super.toString();
    }
}
