public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    public static ToDo addTodo(String task) {
        return new ToDo(task);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
