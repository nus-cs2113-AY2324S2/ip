package jason.tasks;

import jason.tasks.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo parseFromFile(String line) {
        // Check if the task is marked as done
        boolean isDone = line.charAt(4) == 'X';
        String description = line.substring(6).trim();

        Todo todo = new Todo(description);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

}