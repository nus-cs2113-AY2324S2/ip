package schmidt.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public static final String LETTER = "T";
    public static final String INCORRECT_FORMAT_MESSAGE = "Please follow the todo command format\n" +
            "\ttodo <description>";

    /**
     * Constructs a todo task with the specified description.
     *
     * @param description the description of the task.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return a string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[" + LETTER + "]" + super.toString();
    }
}
