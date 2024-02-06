public class Todo extends Task {

    protected String toDo;

    public Todo(String description) {
        super(description);
        this.toDo = this.description.substring(4).trim();
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.toDo;
    }
}
