public class Todo extends Task {
    private Todo(String taskName, int taskId, boolean isCompleted) {
        super(taskName, taskId, isCompleted);
    }

    public Todo(String taskName, int taskId) {
        super(taskName, taskId, false);
    }

    public Task markTaskAsComplete() {
        return new Todo(this.taskName, this.taskId, true);
    }

    public Task markTaskAsIncomplete() {
        return new Todo(this.taskName, this.taskId, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
