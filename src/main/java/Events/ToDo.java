package Events;

public class ToDo extends Task {
    /**
     * Constructs a new ToDo task with the specified description.
     * The completion status is set to false by default (in the Task superclass).
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its type,
     * completion status, and description. The representation follows the format:
     * "[T][status] description", where status is either "X" (for completed tasks)
     * or " " (for tasks that are not completed).
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }


}