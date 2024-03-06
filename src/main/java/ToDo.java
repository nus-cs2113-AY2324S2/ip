public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }

    public String toFileString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }
}