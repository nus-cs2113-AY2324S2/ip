public class Todo extends Task {

    protected String toDo;

    public Todo(String description) throws InvalidTodoException {
        super(description);
        this.toDo = this.description.substring("todo".length()).trim();

        if (this.toDo.isEmpty()) {
            throw new InvalidTodoException();
        }
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.toDo;
    }
}
