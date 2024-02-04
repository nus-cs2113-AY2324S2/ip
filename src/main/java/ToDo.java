public class ToDo extends Task {
    protected static final String TODO_SYMBOL = "[T]";

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskStatus() {
        return TODO_SYMBOL + super.getTaskStatus();
    }
}
