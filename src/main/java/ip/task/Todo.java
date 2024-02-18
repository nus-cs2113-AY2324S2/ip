package ip.task;

public class Todo extends Task {
    private static final int TASK_START_INDEX = 5;

    public Todo(String line) {
        super(line.substring(TASK_START_INDEX));
    }

    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String getDetails() {
        return ("[T]" + super.getDetails());
    }
}
