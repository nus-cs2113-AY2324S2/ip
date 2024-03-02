public class ToDo extends Task {
    public ToDo(String description) throws illegalToDoInput {
        super(description);
        if (description.isEmpty()) {
            throw new illegalToDoInput();
        }
    }

    public String toString() {
        return "[T] " + super.toString();
    }
}
