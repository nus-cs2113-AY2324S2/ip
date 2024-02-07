public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public String editTodo(String description) {
        return description.replace("todo", "");
    }

    @Override
    public String toString() {
        return "[T]" + editTodo(super.toString());
    }
}
