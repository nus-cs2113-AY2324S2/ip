package Tasks;

import Tasks.Task;

/**
 * Represents a class for todo tasks.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
