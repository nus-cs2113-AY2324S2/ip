public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(ToDoArguments toDoArguments) {
        super(toDoArguments.taskName);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
