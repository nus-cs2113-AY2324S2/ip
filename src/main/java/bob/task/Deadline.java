package bob.task;

public class Deadline extends Task {
    private final String dueDate;

    private Deadline(String taskName, boolean isCompleted, String dueDate) {
        super(taskName, isCompleted);
        this.dueDate = dueDate;
    }

    public Deadline(String taskName, String dueDate) {
        super(taskName, false);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public Task markTaskAsComplete() {
        return new Deadline(this.taskName, true, this.dueDate);
    }

    public Task markTaskAsIncomplete() {
        return new Deadline(this.taskName, false, this.dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.dueDate);
    }
}
