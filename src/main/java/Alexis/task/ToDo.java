package Alexis.task;


/**
 * The ToDo class represents a todo task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a ToDo object from a user input string.
     * The input must contain a description.
     *
     * @param input User input string for the Deadline object.
     * @return ToDo object
     */
    protected static ToDo getToDo(String input) {
        return new ToDo(input.trim());
    }

    /**
     * {@inheritDoc}
     *
     * @return A string representation of the task with symbol "T" denoting task type.
     */
    @Override
    public String toString() {
        return String.format("[T]%s\n", super.toString());
    }
}
