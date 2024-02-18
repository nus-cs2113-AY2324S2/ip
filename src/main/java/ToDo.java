public class ToDo extends Task {
    public static final String TODO_STATUS = "[T]";
    public ToDo (String task) {
        super(task);
    }

    @Override
    public String toString() {
        return TODO_STATUS + super.toString();
    }

    @Override
    public String toFileFormat() {
        return TODO_STATUS + super.toFileFormat();
    }
}
