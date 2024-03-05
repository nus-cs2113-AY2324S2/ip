package oley.tasks;

/**
 * Represents a task of Todo type. A Todo object corresponds to a task with its name, and state of done being specified.
 * It is a subclass of Task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Override the toString method of its parent class Task.
     * Returns the type, state and name of task being printed and shown under a specified format to the users.
     * e.g. [T][#] Sleep
     *
     * @return The type, done state and name of task under a specified format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Override the format method of its parent class Task.
     * Returns the type, state and name of task being recorded under a specified format in the data file.
     * e.g. 1todo Sleep
     *
     * @return The type, done state and name of task under a specified format.
     */
    @Override
    public String format() {
        if (checkDone()) {
            return "1todo " + getTaskName();
        } else {
            return "0todo " + getTaskName();
        }
    }
}
