public class Todo extends Task {

    protected String toDo;

    public Todo(String description) throws InvalidTodoException {
        super(description);
        this.toDo = this.description.substring("todo".length()).trim();

        if (this.toDo.length() < 1) {
            throw new InvalidTodoException("User did not enter any task information.");
        }
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.toDo;
    }
}
