public class ToDo extends Task {

    public ToDo(String description) {
        super(description.substring(5));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
