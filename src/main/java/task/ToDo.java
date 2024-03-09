package task;

/**
 * Representation of a ToDo task.
 */

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getTaskAsString() {
        return "T | " + this.getDoneAsInteger() + " | " + this.description;
    }
}
