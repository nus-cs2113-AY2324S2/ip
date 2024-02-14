package artemis.tasks;

public class Deadline extends Task {
    protected String dueDate;
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", super.getStatusIcon(), super.taskName, dueDate);
    }
}
