public class ToDo extends Task {

    public ToDo(String description, Boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T] " + super.getStatusIcon() + " " + super.toString();
    }
}
