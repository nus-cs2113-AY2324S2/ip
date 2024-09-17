package Event;

/**
 * Represents a "to-do" type task within the application.
 * A <code>todo</code> task is a basic task type with a description but
 * without any date/time associated with it.
 * This class extends the <code>Task</code> class, inheriting its basic
 * structure while adding specific behavior for to-do tasks, such as
 * custom string representations for display and storage.
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do task with the specified description. The
     * description is expected to contain the task details. The prefix
     * "todo" is trimmed from the input.
     *
     * @param description The full command input for creating the to-do,
     * including the prefix "todo".
     */
    public ToDo(String description) {
        super(description);
        this.description = description.substring(5).trim();
        this.eventType = "[T]";
    }

    /**
     * Generates a string representation of the todo task. This method
     * overrides the <code>toString</code> method in the superclass
     * <code>Task</code> to provide details specific to to-do tasks.
     *
     * @return A string representation of the to-do task, including its
     * status, which is done or not done, as well as its description.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Provides a string formatted for storage purposes. This includes its
     * type, completion status, and description.
     * It is formatted in a way that is suitable for file storage and later
     * retrieval.
     *
     * @return A string representation of the to-do task designed for storage,
     * containing the task's type, completion status, and description.
     */
    @Override
    public String toStorageString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
