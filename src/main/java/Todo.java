public class Todo extends Task {
    private Todo(String taskName, int taskId, boolean isCompleted) {
        super(taskName, taskId, isCompleted);
    }
    public Todo(String taskName, int taskId) {
        super(taskName, taskId, false);
    }

    public Task completeTask() {
        return new Todo(this.taskName, this.taskId, true);
    }

    public Task uncompleteTask() {
        return new Todo(this.taskName, this.taskId, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
