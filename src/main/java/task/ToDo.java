package task;

/**
 * Task Type-Todo
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        identity = "[T]";
    }
}
