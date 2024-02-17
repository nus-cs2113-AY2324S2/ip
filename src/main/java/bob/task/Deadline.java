package bob.task;

public class Deadline extends Task {
    private final String dueDate;

    private Deadline(String taskName, int taskId, boolean isCompleted, String dueDate) {
        super(taskName, taskId, isCompleted);
        this.dueDate = dueDate;
    }

    public Deadline(String taskName, int taskId, String dueDate) {
        super(taskName, taskId, false);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public Task markTaskAsComplete() {
        return new Deadline(this.taskName, this.taskId, true, this.dueDate);
    }

    public Task markTaskAsIncomplete() {
        return new Deadline(this.taskName, this.taskId, false, this.dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.dueDate);
    }
}
