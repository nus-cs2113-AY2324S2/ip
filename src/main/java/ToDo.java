public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(ToDoArguments toDoArguments) {
        this(toDoArguments.taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
