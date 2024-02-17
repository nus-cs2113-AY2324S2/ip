package bob.task;

public class Todo extends Task {
    private Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    public Todo(String taskName) {
        super(taskName, false);
    }

    public Task markTaskAsComplete() {
        return new Todo(this.taskName, true);
    }

    public Task markTaskAsIncomplete() {
        return new Todo(this.taskName, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
