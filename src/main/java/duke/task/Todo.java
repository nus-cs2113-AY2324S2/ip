package duke.task;

/**
 * Represents the Todo task of the Duke chatbot.
 * Todo tasks are tasks with neither a specific deadline nor a start/end date.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo object with the specified description.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo task to the specific format for saving to the text file.
     *
     * @return A formatted string containing the Todo task for saving.
     */
    @Override
    public String toDisk() {
        return "T" + super.toDisk() + System.lineSeparator();
    }

    /**
     * Converts the Todo task to the specific format for displaying on the screen.
     *
     * @return A formatted string containing the Todo task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
