public class Todo extends Task{
    public Todo(String command) {
        super();
        this.taskName = command.replace("todo", "").trim();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
