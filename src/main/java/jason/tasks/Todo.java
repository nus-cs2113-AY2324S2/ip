package jason.tasks;

import jason.tasks.Task;
/**
 * Represents a todo task.
 * Todo tasks are tasks without any date/time attached to them.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo instance with a description.
     *
     * @param description A string describing the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task,
     * including the type of task and its description.
     *
     * @return A string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parses a line from a file to create a Todo task.
     * This static method is used to load todo tasks from a storage file.
     *
     * @param line A string from the storage file representing a todo task.
     * @return A Todo object representing the task described in the line.
     */
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