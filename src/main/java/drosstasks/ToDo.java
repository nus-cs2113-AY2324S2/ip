package drosstasks;
import drosstasks.Task;

/**
 * Represents a to-do task without a specific deadline or timing.
 * Inherits from the {@link Task} superclass, focusing on tasks that need to be done without time constraints.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo instance with the specified description.
     *
     * @param description The textual description of the to-do task.
     */

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its completion status and description.
     *
     * @return A formatted string indicating the to-do's details.
     */
    @Override
    public String toString() {
        String statusMark = this.isCompleted() ? "x" : " "; // Mark with 'x' if completed
        return "[T]" + "[" + statusMark + "] " + super.toString();
    }
}
