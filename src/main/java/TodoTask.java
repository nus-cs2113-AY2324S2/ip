public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
