package winter.task;

/**
 * Represents a <code>Todo</code> object which contains information about a Todo task
 * that can be modified
 */
public class Todo extends Task {
    private static final String indent = "   ";
    public Todo(int order, boolean marked, String toDoName) {
        super(order,marked,toDoName);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        String typeCheckbox = "[T]";
        return indent + typeCheckbox + " " + this.getDoneCheckbox() + " " + this.getTaskName();
    }

}
