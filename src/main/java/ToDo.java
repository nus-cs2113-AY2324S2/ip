public class ToDo extends Task {

    public ToDo(String description) {
        super(description.substring(5));
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }
}
