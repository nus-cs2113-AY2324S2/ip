package gab.task;

/**
 * Todo class that create todo task with name
 */

public class ToDo extends Task {
    public static final String TODO_STATUS = "[T]";

    /**
     * Initialise new todo task with name
     * @param task todo task name
     */

    public ToDo (String task) {
        super(task);
    }

    /**
     * Returns format of todo tasks to be printed
     *
     * @return string format of todo task to be printed
     */

    @Override
    public String toString() {
        return TODO_STATUS + super.toString();
    }

    /**
     * Convert todo task to a file format to be displayed in external data file
     *
     * @return format of todo task to be display
     */

    @Override
    public String toFileFormat() {
        return TODO_STATUS + super.toFileFormat();
    }
}
