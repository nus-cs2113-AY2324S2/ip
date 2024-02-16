package winter.task;
public class ToDo extends Task {
    private static final String indent = "   ";
    public ToDo (int order,boolean marked, String toDoName) {
        super(order,marked,toDoName);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        String typeCheckbox = "[T]";
        return indent + typeCheckbox + " " + this.doneCheckbox + this.taskName;
    }

}
