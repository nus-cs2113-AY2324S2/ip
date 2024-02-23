package Quokka.tasks;

import Quokka.tasks.Task;


public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone      The completion status of the todo task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parses a string representation of a todo task and returns a Todo object.
     *
     * @param data   The string representation of the todo task.
     * @param isDone The completion status of the todo task.
     * @return A Todo object parsed from the string representation.
     */
    public static Todo parseFromString(String data, boolean isDone) {

        String description = data.trim();
        return new Todo(description, isDone);
    }
}