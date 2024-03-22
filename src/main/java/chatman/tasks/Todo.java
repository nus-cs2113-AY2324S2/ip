package chatman.tasks;

/**
 * Implements template for any task with no provided timings.
 *
 * @author LWachtel1
 * */
public class Todo extends Task {

    private String todoSymbol = "[T]";

    /**
     * Constructor for Todo.
     *
     * @param description Description of task.
     */
    public Todo(String description, String command) {
        super(description, command);
    }

    /**
     * Returns Todo subclass label, completion checkbox, and task description as 1 String.
     *
     * @return String representation of Todo: [T], [X] or [ ], task description.
     */
    @Override
    public String toString() {
        return todoSymbol + super.toString();
    }

}
